package com.bignerdranch.android.aye_ideacanvas

import android.os.Handler
import android.content.Context
import android.util.Log
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.GlobalScope
import java.sql.Connection
import java.sql.DriverManager
import java.sql.SQLException

class IdeaCanvasRepository private constructor (
    context: Context,
    private val mainThreadHandler: Handler,
    private val coroutineScope: CoroutineScope = GlobalScope
) {
    private fun insertData(data: String, whichTable: String) {
        Thread {
            try {
                Class.forName("org.postgresql.Driver")

                val conn: Connection = DriverManager.getConnection(
                    "jdbc:postgresql://<YOUR_DB_HOST>:<PORT>/<DATABASE_NAME>?sslmode=require",
                    "<YOUR_DB_USER>",
                    "<YOUR_DB_PASSWORD>"
                )

                val statement = conn.prepareStatement("INSERT INTO $whichTable($data) VALUES (?)")
                statement.setString(1, data)
                statement.executeUpdate()

                conn.close()

                mainThreadHandler.post {
                    Log.d("Database Action", "Data inserted successfully")
                }
            } catch (e: SQLException) {
                e.printStackTrace()
                mainThreadHandler.post {
                    Log.d("Database Action", "Failed to insert data")
                }
            } catch (e: ClassNotFoundException) {
                e.printStackTrace()
                mainThreadHandler.post {
                    Log.d("Database Action", "Failed to load JDBC driver")
                }
            }
        }.start()
    }

    private fun fetchData(name: String, whichTable: String) {
        Thread {
            try {
                Class.forName("org.postgresql.Driver")

                val conn: Connection = DriverManager.getConnection(
                    "jdbc:postgresql://<YOUR_DB_HOST>:<PORT>/<DATABASE_NAME>?sslmode=require",
                    "<YOUR_DB_USER>",
                    "<YOUR_DB_PASSWORD>"
                )

                val statement = conn.prepareStatement("SELECT $name FROM $whichTable ORDER BY random() LIMIT 1")
                val resultSet = statement.executeQuery()

                if (resultSet.next()) {
                    val randomItem = resultSet.getString("name")

                    mainThreadHandler.post {
                        Log.d("Database Action", "Random Item: $randomItem")
                    }
                } else {
                    mainThreadHandler.post {
                        Log.d("Database Action", "No data found")
                    }
                }

                conn.close()
            } catch (e: SQLException) {
                e.printStackTrace()
                mainThreadHandler.post {
                    Log.d("Database Action", "Failed to fetch data")
                }
            } catch (e: ClassNotFoundException) {
                e.printStackTrace()
                mainThreadHandler.post {
                    Log.d("Database Action", "Failed to load JDBC driver")
                }
            }
        }.start()
    }
}