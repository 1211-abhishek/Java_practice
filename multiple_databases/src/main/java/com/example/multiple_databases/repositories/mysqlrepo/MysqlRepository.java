package com.example.multiple_databases.repositories.mysqlrepo;

import com.example.multiple_databases.entity.mysql.MysqlEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MysqlRepository extends JpaRepository<MysqlEntity, Integer> {
}
