def buildApp() {
  echo 'started build'
  sh 'mvn --debug clean install'
}

def testApp() {
  echo 'started build'
  sh 'mvn test --debug'
}

def deployApp() {
  echo 'deploying the app...'
}

return this
