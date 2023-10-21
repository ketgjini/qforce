pipeline {
  
  agent any
  
  stages {
    
    stage("build") {
      
      steps {
        echo 'building the app'
        withMaven(maven:'Maven-3.9.1') {
          sh 'mvn install'
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
