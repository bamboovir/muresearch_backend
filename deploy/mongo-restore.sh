# https://docs.mongodb.com/manual/reference/program/mongorestore/#bin.mongorestore
sudo mongorestore --archive=muresearch.gz --gzip --host localhost:27017 --username admin --password password --authenticationDatabase admin  --db 'muresearch'
