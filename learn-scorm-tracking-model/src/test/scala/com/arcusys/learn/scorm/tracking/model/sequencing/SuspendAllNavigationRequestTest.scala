package com.arcusys.learn.scorm.tracking.model.sequencing

@org.junit.runner.RunWith(classOf[org.scalatest.junit.JUnitRunner])
class SuspendAllNavigationRequestTest extends NavigationRequestServiceTestBase(NavigationRequestType.SuspendAll) {
  "Suspend All navigation request" should "succeed if current activity is defined (12.1.1)" in {
    expectResult(NavigationResponseWithTermination, rootOnlyTree(hasCurrent = true))
  }

  it should "fail if current activity is not defined (12.2.1)" in {
    expectResult(NavigationResponseInvalid, rootOnlyTree())
  }
}