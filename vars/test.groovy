

def unitTest(){
    script {
       // try {
            sh './gradlew test'
            //echo "Unit Test Passed"
     //}
        // catch(e){
        //     echo "Test failed : ${e}"
        // }
        
    }
}

def integrationTest(){
    script{
       echo "integration test not yet implemented"
    }
}

def regressionTest() {

    script{
        echo "regression test not yet implemented"
    }
}

def sonarQubeTest(){
    script{
        sh './gradlew sonar'
    }
}