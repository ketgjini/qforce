pipeline {
  agent any
  
  tools {
    maven 'Maven-3.9.1'
  }
  
  stages {
    
    stage("build") {
      when {
        expression {
          BRANCH_NAME == 'dev' || BRANCH_NAME == 'master'
        }
      }
      steps {
        echo 'started build'
        sh 'mvn --debug install'
      }
    }
    stage("test") {
      when {
        expression {
          BRANCH_NAME == 'dev'
        }
      }
      steps {
        echo 'testing the app'
      }
    }
    stage("deploy") {
      when {
        expression {
          BRANCH_NAME == 'master'
        }
      }
      steps {
        echo 'deploying the app'
      }
    }
  }
  post {
    success {
      echo 'pipeline succeeded'
    }
  }
}
