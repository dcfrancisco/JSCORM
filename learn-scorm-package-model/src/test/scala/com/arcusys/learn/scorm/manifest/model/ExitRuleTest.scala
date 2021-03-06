package com.arcusys.learn.scorm.manifest.model

import org.scalatest.FlatSpec
import org.scalatest.matchers.ShouldMatchers
import org.junit.runner.RunWith

@RunWith(classOf[org.scalatest.junit.JUnitRunner])
class ExitRuleTest extends FlatSpec with ShouldMatchers {
  val condition1 = new RuleCondition(ConditionType.ActivityAttempted)
  val condition2 = new RuleCondition(ConditionType.ObjectiveStatusKnown)
  val conditions = Seq(condition1, condition2)

  "Exit rule" can "be constructed" in {
    val set = new RuleConditionSet(conditions, ConditionCombination.All)
    val rule = new ExitConditionRule(set)
    rule.conditions should equal(set)
  }
}
