pipeline {
  
  agent any

  tools {
    maven 'Maven-3.9.1'
  }
  
  stages {
    
    stage("build") {
      
      steps {
        echo 'started build'
        bat 'mvn --debug install'
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
