```
$ vi /etc/httpd/conf/httpd.conf
LoadModule log_forensic_module /usr/lib64/httpd/modules/mod_log_forensic.so 
<IfModule log_forensic_module> 
ForensicLog ${APACHE_LOG_DIR}/forensic.log
</IfModule> 
```