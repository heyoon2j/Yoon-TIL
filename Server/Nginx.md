# Nginx

## Install
* docs: https://www.nginx.com/resources/wiki/start/topics/tutorials/install/
* CentOS
    ```
    #!/bin/bash
    sudo touch /etc/yum.repos.d/nginx.repo
    sudo bash -c 'cat << EOF > /etc/yum.repos.d/nginx.repo
    [nginx]
    name=nginx repo
    baseurl=https://nginx.org/packages/centos/$releasever/$basearch/
    gpgcheck=0
    enabled=1
    EOF'
    sudo yum install -y nginx
    
    ```
    * ```$releasever```: 해당 변수에는 원하는 버전을 적으면 된다.
</br>

## Nginx Setting
* Reference : https://prohannah.tistory.com/136

