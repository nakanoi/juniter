pipeline {
  agent any
  stages {
    stage('Build') {
      steps {
        sh './gradlew build'
      }
    }
    stage('Test Report') {
      steps {
        junit 'build/test-results/**/*.xml'
        jacoco execPattern: 'build/jacoco/jacoco.exec'
      }
    }
    stage('Static Code Analysis') {
      steps {
        checkStyle pattern: 'build/checkstyle/*.xml'
        spotBugs pattern: 'build/spotbugs/*.xml'
      }
    }
  }
}