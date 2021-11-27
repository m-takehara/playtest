package com.uzabase.playtest.gauge.db

import com.thoughtworks.gauge.Step
import com.uzabase.playtest.db.Database
import javassist.NotFoundException
import org.assertj.db.api.Assertions.assertThat
import org.assertj.db.type.Request
import org.assertj.db.type.Source
import java.io.File

class DatabaseStep {

    @Step("DB<dbName>の<schemaName>スキーマの<tableName>テーブルの、<whereColumn>を<whereValue>で取得した一意の<valueColumn>が整数の<value>である",
        "DB<dbName>の<schemaName>スキーマの<tableName>テーブルの、<whereColumn>を<whereValue>で取得した一意の<valueColumn>が小数の<value>である",
        "DB<dbName>の<schemaName>スキーマの<tableName>テーブルの、<whereColumn>を<whereValue>で取得した一意の<valueColumn>が文字列の<value>である")
    fun assertUniqueRecordValue(
        dbName: String,
        schemaName: String,
        tableName: String,
        whereColumn: String,
        whereValue: String,
        valueColumn: String,
        value: String
    ) {
        val config = GaugeDbConfig.get(dbName)
        val source = Source(config.url, config.user, config.password)

        val request = Request(source, "select * from $schemaName.$tableName where $whereColumn = '$whereValue'")

        assertThat(request).row().value(valueColumn).isEqualTo(value)
    }

    @Step("test_dbにテストデータをセットアップする")
    fun setupDatabase() {
        val db = GaugeDbConfig.get("test_db")
        val database = Database(db.driverClass, db.url, db.user, db.password, db.schema)
        val data = javaClass.getResource("/test-db")?.toURI()?.let { File(it) } ?: throw NotFoundException("/test-db not found")
        database.cleanInsert(data )
    }
}