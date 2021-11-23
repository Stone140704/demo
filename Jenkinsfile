pipeline{

      // 定义groovy脚本中使用的环境变量
      environment{
        // 选择构建环境
        ENV = sh(returnStdout: true,script: 'echo $env').trim()
        // docker镜像仓库地址  目前我们使用阿里云的镜像服务
        ORIGIN_REPO =  sh(returnStdout: true,script: 'echo $origin_repo').trim()
        // 项目名称
        PROJECT_NAME =  sh(returnStdout: true,script: 'echo $project_name').trim()
        MODULE_NAME =  sh(returnStdout: true,script: 'echo $module_name').trim()
        // docker镜像标签 示例：master-20210209105814
        //IMAGE_TAG =  sh(returnStdout: true,script: 'echo $branch-`date +%Y%m%d%H%M%S`').trim()
        IMAGE_TAG =  "master-20211123025857"
        // 将容器部署到k8s集群的命名空间
        NAMESPACE = sh(returnStdout: true,script: 'echo $namespace').trim()
      }

      // 定义本次构建使用哪个标签的构建环境
      agent{
        node{
          label 'slave-pipeline'
        }
      }

      // "stages"定义项目构建的多个模块，可以添加多个 “stage”， 可以多个 “stage” 串行或者并行执行
      stages{

        //// 添加第一个stage， 运行源码打包命令
        //stage('Package'){
        //  steps{
        //      container("maven") {
        //          sh "mvn package -P $ENV -U -B -DskipTests"
        //      }
        //  }
        //}
        //
        //// 添加第二个stage, 运行容器镜像构建和推送命令， 用到了environment中定义的groovy环境变量
        //stage('Image Build And Publish'){
        //  steps{
        //      container('docker') {
        //        withCredentials([usernamePassword(credentialsId: 'harbor', usernameVariable: 'username', passwordVariable: 'password')]){
        //           sh 'docker version'
        //           // 刀客编译
        //           sh "docker build -t $ORIGIN_REPO/$PROJECT_NAME/MODULE_NAME:$IMAGE_TAG  ."
        //           sh "docker login -u $username -p $password $ORIGIN_REPO"
        //           // 刀客镜像推送
        //           sh "docker push $ORIGIN_REPO/$PROJECT_NAME/$MODULE_NAME:$IMAGE_TAG"
        //           // 清理镜像
        //           sh 'docker image prune -f'
        //        }
        //     }
        //  }
        //}

        // 添加第三个stage， 将容器部署到k8s集群
        stage('Deploy to Kubernetes') {
            steps {
                kubernetesDeploy(
                    configs: 'deployment.yaml',
                    enableConfigSubstitution: true,
                    kubeconfigId: 'kubeconfig'
                )
            }
        }
      }
    }
