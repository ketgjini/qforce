def grv
pipeline {
  agent any
  
  tools {
    maven 'Maven-3.9.1'
  }
  
  stages {
    stage("init") {
      steps {
        script {
          grv = load "script.groovy"
        }
      }
    }
    stage("build") {
      when {
        expression {
          BRANCH_NAME == 'dev' || BRANCH_NAME == 'master'
        }
      }
      steps {
        script {
          grv.buildApp()
        }
      }
    }
    stage("test") {
      when {
        expression {
          BRANCH_NAME == 'dev'
        }
      }
      steps {
        script {
          grv.testApp()
        }
      }
    }
    stage("deploy") {
      when {
        expression {
          BRANCH_NAME == 'master'
        }
      }
      steps {
        script {
          grv.deployApp()
        }
      }
    }
  }
  post {
    success {
      echo 'pipeline succeeded'
    }
  }
}
