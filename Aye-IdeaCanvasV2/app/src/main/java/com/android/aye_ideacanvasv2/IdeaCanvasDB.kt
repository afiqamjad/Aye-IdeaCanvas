package com.android.aye_ideacanvasv2

import android.util.Log
import com.android.aye_ideacanvasv2.model.Post
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.sql.Connection
import java.sql.DriverManager
import java.sql.SQLException
import java.io.FileInputStream
import java.util.Properties

object IdeaCanvasDB {

    private val props = Properties()

    init {
        loadConfig()
    }

    private fun loadConfig() {
        try {
            val configFile = "path/to/config.properties"
            props.load(FileInputStream(configFile))
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    private val dbUrl: String = props.getProperty("db.url")
    private val dbUsername: String = props.getProperty("db.username")
    private val dbPassword: String = props.getProperty("db.password")

    suspend fun insertData(data: String, whichTable: String) {
        withContext(Dispatchers.IO) {
            try {
                Class.forName("org.postgresql.Driver")

                val conn: Connection = DriverManager.getConnection(
                    dbUrl,
                    dbUsername,
                    dbPassword
                )

                val statement = conn.prepareStatement("INSERT INTO $whichTable($data) VALUES (?)")
                statement.setString(1, data)
                statement.executeUpdate()

                conn.close()

                Log.d("Database Action", "Data inserted successfully")
            } catch (e: SQLException) {
                e.printStackTrace()
                Log.d("Database Action", "Failed to insert data")
            } catch (e: ClassNotFoundException) {
                e.printStackTrace()
                Log.d("Database Action", "Failed to load JDBC driver")
            }
        }
    }

    suspend fun fetchData(name: String, whichTable: String, id: String): String? {
        var dataItem: String? = null

        withContext(Dispatchers.IO) {
            try {
                Class.forName("org.postgresql.Driver")

                val conn = DriverManager.getConnection(
                    dbUrl,
                    dbUsername,
                    dbPassword
                )

                val statement = conn.prepareStatement("SELECT $name FROM $whichTable WHERE id = '$id';")
                val resultSet = statement.executeQuery()

                if (resultSet.next()) {
                    dataItem = resultSet.getString("name")
                    Log.d("Database Action", "Random Item: $dataItem")
                } else {
                    Log.d("Database Action", "No data found")
                }

                conn.close()
            } catch (e: SQLException) {
                e.printStackTrace()
                Log.d("Database Action", "Failed to fetch data")
            } catch (e: ClassNotFoundException) {
                e.printStackTrace()
                Log.d("Database Action", "Failed to load JDBC driver")
            }
        }

        return dataItem
    }

    suspend fun insertPost(postId: Long, userId:Int, title: String, imageUrl: String?, content: String?) {
        withContext(Dispatchers.IO) {
            try {
                Class.forName("org.postgresql.Driver")

                val conn: Connection = DriverManager.getConnection(
                    dbUrl,
                    dbUsername,
                    dbPassword
                )

                val statement = conn.prepareStatement("INSERT INTO post_table(post_id, user_id, title, image_url, content) VALUES (?, ?, ?, ?, ?)")
                statement.setLong(1, postId)
                statement.setInt(2, userId)
                statement.setString(3, title)
                statement.setString(4, imageUrl)
                statement.setString(5, content)
                statement.executeUpdate()

                conn.close()

                Log.d("Database Action", "Data inserted successfully")
            } catch (e: SQLException) {
                e.printStackTrace()
                Log.d("Database Action", "Failed to insert data")
            } catch (e: ClassNotFoundException) {
                e.printStackTrace()
                Log.d("Database Action", "Failed to load JDBC driver")
            }
        }
    }

    suspend fun fetchPost(postId: Long): Post? {
        var post: Post? = null

        withContext(Dispatchers.IO) {
            try {
                Class.forName("org.postgresql.Driver")

                val conn = DriverManager.getConnection(
                    dbUrl,
                    dbUsername,
                    dbPassword
                )

                val statement = conn.prepareStatement("SELECT * FROM post_table WHERE post_id = ?")
                statement.setLong(1, postId)
                val resultSet = statement.executeQuery()

                if (resultSet.next()) {
                    //val userId = resultSet.getInt("user_id")
                    val username = resultSet.getString("username")
                    //val title = resultSet.getString("title")
                    val imageUrl = resultSet.getString("image_url")
                    val content = resultSet.getString("content")

                    post = Post(username, imageUrl, content)
                    Log.d("Database Action", "Post fetched successfully")
                } else {
                    Log.d("Database Action", "No post found with postId: $postId")
                }

                conn.close()
            } catch (e: SQLException) {
                e.printStackTrace()
                Log.d("Database Action", "Failed to fetch post")
            } catch (e: ClassNotFoundException) {
                e.printStackTrace()
                Log.d("Database Action", "Failed to load JDBC driver")
            }
        }

        return post
    }


}