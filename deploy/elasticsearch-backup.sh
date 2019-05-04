# https://www.elastic.co/guide/en/elasticsearch/reference/current/modules-snapshots.html
# https://team2.fi/2015/05/23/easy-backup-and-restore-for-you-elasticsearch-entities/
# https://www.elastic.co/guide/en/elasticsearch/reference/current/docker.html

curl -X PUT "localhost:9200/_snapshot/es_backup" -H 'Content-Type: application/json' -d'
{
  "type": "fs",
  "settings": {
    "location": "es_backup",
    "compress": true
  }
}
'

# 创建备份库
curl -X GET "localhost:9200/_snapshot/es_backup"
# 获取所有备份状态
curl -X GET "localhost:9200/_snapshot/_all"
# 获取当前备份进行中状态
curl -X PUT "localhost:9200/_snapshot/es_backup/es_backup_1?wait_for_completion=true"
# 获取备份信息
curl -X GET "localhost:9200/_snapshot/es_backup/es_backup_1"

# 从备份中恢复
curl -X POST "localhost:9200/_snapshot/my_backup/snapshot_1/_restore"


# sudo docker exec docker_elasticsearch_1 tar Ccf $(dirname /usr/share/elasticsearch/data) - $(basename /usr/share/elasticsearch/data) | t
  ar Cxf ~/es/data  -


