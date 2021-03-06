package com.arcusys.learn.quiz.service

import com.arcusys.scorm.util.FileSystemUtil
import java.io.FileInputStream
import com.arcusys.scorm.generator.file.QuizPackageGenerator
import com.arcusys.scorm.deployer.PackageProcessor

import org.scala_tools.subcut.inject.BindingModule
import com.arcusys.learn.web.ServletBase
import com.arcusys.learn.ioc.Configuration

class GeneratedPackagesService(configuration: BindingModule) extends ServletBase(configuration) {
  def this() = this(Configuration)
  import storageFactory._

  get("/Zip/:quizID") {
    contentType = "application/zip"
    
    val quizID = parameter("quizID").intRequired
    val quiz = quizStorage.getByID(quizID).get
    val generator = new QuizPackageGenerator(quiz)
    val filename = generator.generateZip
    
    log.debug(FileSystemUtil.getZipPackageDir + filename)
    val is = new FileInputStream(FileSystemUtil.getZipPackageDir + filename)
    org.scalatra.util.io.copy(is, response.getOutputStream)
    is.close()
  }
  
  post("/ZipInstall/:quizID") {
    val quizID = parameter("quizID").intRequired
    val quiz = quizStorage.getByID(quizID).get
    val generator = new QuizPackageGenerator(quiz)
    val filename = generator.generateZip
    
    PackageProcessor.processPackageAndGetID(quiz.title, "", quiz.id.toString)
  }
}
