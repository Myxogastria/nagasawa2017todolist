package com.example.todolist

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.jdbc.Sql
import org.springframework.test.context.junit.jupiter.SpringExtension

@ExtendWith(SpringExtension::class)
@SpringBootTest
@Sql(statements = arrayOf("DELETE FROM task"))
class JdbcTaskRepositoryTest {
    @Autowired
    private lateinit var sut: JdbcTaskRepository

    @Test
    fun 何も作成しなければfindAllは空のリストを返すこと(){
        // このテストの前にdb.mv.dbを削除しておく
        val got = sut.findAll()
        assertThat(got).isEmpty()
    }

    @Test
    fun createで作成したタスクをfindByIdで取得できること(){
        val task = sut.create("TEST")
        val got = sut.findById(task.id)
        assertThat(got).isEqualTo(task)
    }
}