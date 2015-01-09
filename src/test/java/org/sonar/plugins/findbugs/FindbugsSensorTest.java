/*
 * SonarQube Findbugs Plugin
 * Copyright (C) 2012 SonarSource
 * dev@sonar.codehaus.org
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 3 of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this program; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02
 */
package org.sonar.plugins.findbugs;

import com.google.common.collect.Lists;
import edu.umd.cs.findbugs.BugInstance;
import edu.umd.cs.findbugs.ClassAnnotation;
import edu.umd.cs.findbugs.MethodAnnotation;
import edu.umd.cs.findbugs.SourceLineAnnotation;
import org.junit.Test;
import org.sonar.api.batch.SensorContext;
import org.sonar.api.batch.fs.internal.DefaultFileSystem;
import org.sonar.api.batch.fs.internal.DefaultInputFile;
import org.sonar.api.profiles.RulesProfile;
import org.sonar.api.resources.JavaFile;
import org.sonar.api.resources.Project;
import org.sonar.api.resources.ProjectFileSystem;
import org.sonar.api.resources.Resource;
import org.sonar.api.rules.Violation;
import org.sonar.plugins.java.api.JavaResourceLocator;

import java.io.File;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;

import static org.fest.assertions.Assertions.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class FindbugsSensorTest extends FindbugsTests {


  private DefaultFileSystem fs = new DefaultFileSystem();

  @Test
  public void shouldNotAnalyseIfJavaProjectButNoSource() {
    Project project = mock(Project.class);
    FindbugsSensor sensor = new FindbugsSensor(null, null, null, mockJavaResourceLocator(), fs);
    assertThat(sensor.shouldExecuteOnProject(project)).isFalse();
  }

  private void addJavaFileToFs() {
    DefaultInputFile inputFile = new DefaultInputFile("src/foo/bar.java");
    inputFile.setLanguage("java");
    fs.add(inputFile);
  }

  @Test
  public void shouldNotAnalyseIfJavaProjectButNoRules() {
    Project project = mock(Project.class);
    addJavaFileToFs();
    FindbugsSensor sensor = new FindbugsSensor(RulesProfile.create(), null, null, null, fs);
    assertThat(sensor.shouldExecuteOnProject(project)).isFalse();
  }

  @Test
  public void shouldAnalyse() {
    Project project = mock(Project.class);
    addJavaFileToFs();
    FindbugsSensor sensor = new FindbugsSensor(createRulesProfileWithActiveRules(), null, null, mockJavaResourceLocator(), fs);
    assertThat(sensor.shouldExecuteOnProject(project)).isTrue();
  }

  @Test
  public void shouldExecuteFindbugs() throws Exception {
    Project project = createProject();
    FindbugsExecutor executor = mock(FindbugsExecutor.class);
    SensorContext context = mock(SensorContext.class);

    BugInstance bugInstance = new BugInstance("AM_CREATES_EMPTY_ZIP_FILE_ENTRY", 2);
    String className = "org.sonar.commons.ZipUtils";
    String sourceFile = "org/sonar/commons/ZipUtils.java";
    int startLine = 107;
    ClassAnnotation classAnnotation = new ClassAnnotation(className, sourceFile);
    bugInstance.add(classAnnotation);
    MethodAnnotation methodAnnotation = new MethodAnnotation(className, "_zip", "(Ljava/lang/String;Ljava/io/File;Ljava/util/zip/ZipOutputStream;)V", true);
    methodAnnotation.setSourceLines(new SourceLineAnnotation(className, sourceFile, startLine, 0, 0, 0));
    bugInstance.add(methodAnnotation);
    Collection<ReportedBug> collection = Arrays.asList(new ReportedBug(bugInstance));
    when(executor.execute(false, false)).thenReturn(collection);
    JavaResourceLocator javaResourceLocator = mockJavaResourceLocator();
    when(javaResourceLocator.classFilesToAnalyze()).thenReturn(Lists.newArrayList(new File("file")));
    when(context.getResource(any(Resource.class))).thenReturn(new JavaFile("org.sonar.MyClass"));

    FindbugsSensor analyser = new FindbugsSensor(createRulesProfileWithActiveRules(), FakeRuleFinder.create(), executor, javaResourceLocator, fs);
    analyser.analyse(project, context);

    verify(executor).execute(false, false);
    verify(context, times(1)).saveViolation(any(Violation.class));
  }

  @Test
  public void should_not_execute_if_no_compiled_class_available() throws Exception {
    Project project = createProject();
    FindbugsExecutor executor = mock(FindbugsExecutor.class);
    SensorContext context = mock(SensorContext.class);
    JavaResourceLocator javaResourceLocator = mockJavaResourceLocator();
    when(javaResourceLocator.classFilesToAnalyze()).thenReturn(Collections.<File>emptyList());

    FindbugsSensor sensor = new FindbugsSensor(createRulesProfileWithActiveRules(), null, executor, mockJavaResourceLocator(), fs);

    sensor.analyse(project, context);
    verify(executor, never()).execute();

  }

  @Test
  public void shouldIgnoreNotActiveViolations() throws Exception {
    Project project = createProject();
    FindbugsExecutor executor = mock(FindbugsExecutor.class);
    SensorContext context = mock(SensorContext.class);
    when(context.getResource(any(Resource.class))).thenReturn(new JavaFile("org.sonar.MyClass"));

    BugInstance bugInstance = new BugInstance("UNKNOWN", 2);
    String className = "org.sonar.commons.ZipUtils";
    String sourceFile = "org/sonar/commons/ZipUtils.java";
    ClassAnnotation classAnnotation = new ClassAnnotation(className, sourceFile);
    bugInstance.add(classAnnotation);
    Collection<ReportedBug> collection = Arrays.asList(new ReportedBug(bugInstance));
    when(executor.execute()).thenReturn(collection);

    FindbugsSensor analyser = new FindbugsSensor(createRulesProfileWithActiveRules(), FakeRuleFinder.create(), executor, mockJavaResourceLocator(), fs);
    analyser.analyse(project, context);

    verify(context, never()).saveViolation(any(Violation.class));
  }

  private Project createProject() {
    ProjectFileSystem fileSystem = mock(ProjectFileSystem.class);
    when(fileSystem.hasJavaSourceFiles()).thenReturn(Boolean.TRUE);

    Project project = mock(Project.class);
    when(project.getFileSystem()).thenReturn(fileSystem);
    return project;
  }

  private static JavaResourceLocator mockJavaResourceLocator() {
    JavaResourceLocator javaResourceLocator = mock(JavaResourceLocator.class);
    Resource resource = mock(Resource.class);
    when(javaResourceLocator.findResourceByClassName(anyString())).thenReturn(resource);
    return javaResourceLocator;
  }

}
