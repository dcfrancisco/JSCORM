package com.arcusys.learn.quiz.service

import com.arcusys.learn.quiz.model.Quiz
import org.scala_tools.subcut.inject.BindingModule
import com.arcusys.learn.web.ServletBase
import com.arcusys.learn.ioc.Configuration

class QuizService(configuration: BindingModule) extends ServletBase(configuration) {
  def this() = this(Configuration)

  import storageFactory._

  val jsonModel = new JsonModelBuilder[Quiz](quiz =>
    Map("id" -> quiz.id,
      "description" -> quiz.description,
      "title" -> quiz.title,
      "welcomePageContent" -> quiz.welcomePageContent,
      "finalPageContent" -> quiz.finalPageContent,
      "questionCount" -> quizQuestionStorage.getCount(quiz.id))
  )

  get("/") {
    jsonModel(quizStorage.getAll)
  }

  get("/:id") {
    val id = parameter("id").intRequired
    jsonModel(quizStorage.getByID(id))
  }

  post("/") {
    val title = parameter("title").required
    val description = parameter("description").required
    val welcomePageContent = parameter("welcomePageContent").required
    val finalPageContent = parameter("finalPageContent").required
    val quizId = quizStorage.createAndGetID(new Quiz(0, title, description, welcomePageContent, finalPageContent))
    jsonModel(quizStorage.getByID(quizId).get)
  }

  post("/update/:id") {
    val id = parameter("id").intRequired
    val title = parameter("title").required
    val description = parameter("description").required
    val welcomePageContent = parameter("welcomePageContent").required
    val finalPageContent = parameter("finalPageContent").required
    val quiz = new Quiz(id, title, description, welcomePageContent, finalPageContent)
    quizStorage.modify(quiz)
    jsonModel(quiz)
  }

  post("/delete/:id") {
    val id = parameter("id").intRequired
    quizStorage.delete(id)
  }
}