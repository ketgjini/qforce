pipeline {
  
  agent any
  
  stages {
    
    stage("build") {
      
      steps {
        echo 'started build'
        withMaven(maven: 'Maven-3.9.1') {
           echo 'Inside withMaven'
           bat 'mvn --debug install'
        }
      }
    }
    stage("test") {
      
      steps {
        echo 'testing the app'
      }
    }
    stage("deploy") {
      
      steps {
        echo 'deploying the app'
      }
    }
  }
}
