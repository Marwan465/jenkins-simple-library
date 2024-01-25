


def openshiftDeploy(String OPENSHIFT_CONFIG_FILE, String nameSpace, String yamlPath){
        script {
                    withCredentials([file(credentialsId: OPENSHIFT_CONFIG_FILE, variable: 'KUBE_CONFIG_FILE')]) {
           env.KUBECONFIG = sh(script: "echo \$KUBE_CONFIG_FILE", returnStdout: true).trim() 
                    sh "chmod 600 \$KUBECONFIG"
                    sh "oc project ${nameSpace} --kubeconfig=\$KUBECONFIG"
                    sh "oc apply -f ${yamlPath} --kubeconfig=\$KUBECONFIG"
                    
                    }
                }
}

def asService(String OPENSHIFT_CONFIG_FILE, String deploymentName) {
        script {
             withCredentials([file(credentialsId: OPENSHIFT_CONFIG_FILE, variable: 'KUBE_CONFIG_FILE')]) {
           env.KUBECONFIG = sh(script: "echo \$KUBE_CONFIG_FILE", returnStdout: true).trim()
            sh "oc expose deployment ${deploymentName} --kubeconfig=\$KUBECONFIG"
  
        }
        }
}

def createRoute(String OPENSHIFT_CONFIG_FILE, String serviceName) {

       script {
             withCredentials([file(credentialsId: OPENSHIFT_CONFIG_FILE, variable: 'KUBE_CONFIG_FILE')]) {
           env.KUBECONFIG = sh(script: "echo \$KUBE_CONFIG_FILE", returnStdout: true).trim()
            
            
            sh "oc create route edge --service=${serviceName}"
        }
    }
}
