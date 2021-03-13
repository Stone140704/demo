pipeline{

      // 定义groovy脚本中使用的环境变量
      environment{
        // 项目名称
        PROJECT_NAME =  sh(returnStdout: true,script: 'echo $project_name').trim()
        // docker镜像标签 示例：master-20210209105814
        IMAGE_TAG =  sh(returnStdout: true,script: 'echo $branch-`date +%Y%m%d%H%M%S`').trim()
        // 将容器部署到k8s集群的命名空间
        NAMESPACE = sh(returnStdout: true,script: 'echo $namespace').trim()
        // 镜像地址
        IMAGE_URL = sh(returnStdout: true,script: 'echo $image_url').trim()
      }

      // 定义本次构建使用哪个标签的构建环境
      agent{
        node{
          label 'slave-pipeline'
        }
      }

      // "stages"定义项目构建的多个模块，可以添加多个 “stage”， 可以多个 “stage” 串行或者并行执行
      stages{

        // 添加第三个stage， 将容器部署到k8s集群
        stage('Deploy to Kubernetes') {
            steps {
                kubernetesDeploy(
                    // deployment.yaml文件的位置，这里是与Jenkinsfile文件在同一个目录下
                    configs: 'deployment.yaml',
                    // 开启配置替换，会使用jenkins的环境变量替换deployment.yaml文件中的变量
                    enableConfigSubstitution: true,
                    // 访问k8s的凭证id
                    kubeconfigId: 'kubeconfig',
                    // 设置secret的命名空间，这个secret就是用来拉取私有镜像的用户名密码
                    secretNamespace: "$NAMESPACE",
                    // 设置secret的名字，不设置则会自动生成
                    secretName: 'my-secret',
                    // 指定私有镜像仓库，并指定在jenkins中配置的登录仓库的用户名和密码的credential id
                    dockerCredentials: [
                        [credentialsId: 'docker-pwd', url: "$REGISTRY_URL"],
                    ],
                )
            }
        }
      }
    }