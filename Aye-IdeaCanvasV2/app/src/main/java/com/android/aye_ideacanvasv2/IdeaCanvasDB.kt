package com.android.aye_ideacanvasv2

import android.util.Log
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.sql.Connection
import java.sql.DriverManager
import java.sql.SQLException

object IdeaCanvasDB {
    suspend fun insertData(data: String, whichTable: String) {
        withContext(Dispatchers.IO) {
            try {
                Class.forName("org.postgresql.Driver")

                val conn: Connection = DriverManager.getConnection(
                    "jdbc:postgresql://aye-ideacanvas-14031.7tt.aws-us-east-1.cockroachlabs.cloud:26257/defaultdb?sslmode=require",
                    "elliot",
                    "<YOUR_DB_PASSWORD>"
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
                    "jdbc:postgresql://aye-ideacanvas-14031.7tt.aws-us-east-1.cockroachlabs.cloud:26257/defaultdb?sslmode=require",
                    "elliot",
                    "<YOUR_DB_PASSWORD>"
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

    suspend fun insertPost(postId: Int, userId:Int, title: String, imageUrl: String?, content: String?) {
        withContext(Dispatchers.IO) {
            try {
                Class.forName("org.postgresql.Driver")

                val conn: Connection = DriverManager.getConnection(
                    "jdbc:postgresql://aye-ideacanvas-14031.7tt.aws-us-east-1.cockroachlabs.cloud:26257/defaultdb?sslmode=require",
                    "elliot",
                    "hPX6uyZJIyfoqHQJpGwLMg"
                )

                val statement = conn.prepareStatement("INSERT INTO post_table(post_id, user_id title, image_url, content) VALUES (?, ?, ?, ?, ?)")
                statement.setInt(1, postId)
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


}