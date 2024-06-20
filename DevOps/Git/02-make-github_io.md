# github.io
* github 저장소를 활용해 정적인 사이트 호스팅이 가능
* Site Address: {user}.github.io

## Static Site Generator
* **Hugo**: Golang 기반 정적인 블로그 생성기
* **Hexo**: Node.js 기반 정적인 블로그 생성기


## Hexo
### Requirements Hexo
* Git Install
* Node.js Install(https://nodejs.org/en/)
> Minimum Node.js version: 10.13.0 (2020.08.09)
* ``` $ npm istall -g hexo-cli``` : hexo 설치



### Init hexo
``` 
$ hexo init <folder> 
$ cd <folder>
$ npm install   // package.json을 확인하여 필요한 패키지 설치
$ npm install hexo-deployer-git --save  // 자동 배포를 위해
``` 

* hexo folder
```shell script
.
├── _config.yml
├── package.json
├── scaffolds
├── source
|   ├── _drafts
|   └── _posts
└── themes
```
 **_config.yml** : Site configuration file
 **package.json** : Application Data(Version, Dependency etc.)
 **themes** : Theme folder
 **source** : Source folder, site's content.
 
 ### Configuration
 * **_config.yml**
 
 1. **\# Site**
    ```yaml
    # Site
    title:          ## Site Tile
    subtitle: ''    ## Site Subtitle
    description: '' ## Site Description
    keywords:       ## Site Keywords ex> 
    author:         ## Name
    language:       ## Site Language ex> en
    timezone: ''    ## Site Timezone ex> America/New_York, Asia/Seoul
    ```
 
 2. **\# URL**
    ```yaml
    # URL
    url: 
    root: /
    permalink: :year/:month/:day/:title/
    permalink_defaults:
    pretty_urls:
      trailing_index: true
      trailing_html: true 
    ```
    * github.io와 연동하기 위해서는 URL에 해당하는 주소 작성

3. **\# Writing**
    ```yaml
   # Writing
   new_post_name: :title.md # File name of new posts
   default_layout: post
   titlecase: false # Transform title into titlecase
   external_link:
     enable: true # Open external links in new tab
     field: site # Apply to the whole site
     exclude: ''
   filename_case: 0
    ```
    * new post 명령시 생성되는 파일의 이름은 Placeholder를 이용해 커스터마이징할 수 있다. ex> :year:month:day-:title.md

4. **\# Deployment**
    ```yaml
    deploy:
      type: 
      repo:    
    ```
    * git과 연동하기 위해서는 type: git / repo: github.io repository 주소
   
### Write & Deploy

```shell script
$ hexo new {layout} {file name}     // layout에 해당하는 파일 생성
$ hexo clean && hexo generate       // generate 명령시, html 파일로 변환되어, 'public/' 폴더로 이동
$ hexo clean && hexo deploy         // 배포
```


