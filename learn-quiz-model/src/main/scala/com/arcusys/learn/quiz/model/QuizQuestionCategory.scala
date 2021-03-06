package com.arcusys.learn.quiz.model

/**
 * A question category in quiz database
 * @param id            Unique internal ID of category
 * @param title         Title of category (used mostly for admin purposes to quickly find category in list)
 * @param description   A more detailed description of the category used when the admin or tested user need an explanation
 * @param quizID        ID of the quiz which is parent to this category
 * @param parentID      ID of the parent category, if this is a subcategory. None if category is in root of quiz
 */
class QuizQuestionCategory(val id: Int, val title: String, val description: String, val quizID: Int, val parentID: Option[Int])