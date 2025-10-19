# PostgreSQL Demo Database
run
```bash
docker compose up -d
```


Using publich schema of demo_db 

# Users

## Create table some_data
```postgresql
create  table some_data (
    id SERIAL primary key,
    my_data text
);
```
