
def getCommitHash(){
    return sh(script: 'git rev-parse --short HEAD', returnStdout: true).trim()
}

def buildDockerImage(String DockerRegistry, String image){
    
    script{
        def commitHash = getCommitHash()
        def dockerImage = docker.build("${DockerRegistry}/${image}:${CommitHash}")
        return [commitHash, dockerImage]
    }

}

def pushDockerImage(String registryCredential, String dockerImage) {
    script {
        def commitHash = getCommitHash()
        withCredentials([usernamePassword(credentialsId: registryCredential, usernameVariable: 'DOCKER_HUB_USERNAME', passwordVariable: 'DOCKER_HUB_PASSWORD')])   {
    sh 'echo $DOCKER_HUB_PASSWORD | docker login -u $DOCKER_HUB_USERNAME --password-stdin'                		
	echo 'Login Completed'
    dockerImage.push()
    }
        return commitHash
    }
}
