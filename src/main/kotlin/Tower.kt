import java.io.BufferedReader
import java.io.File
import java.io.FileReader
import java.nio.charset.Charset
import java.sql.Connection
import java.sql.DriverManager


class Tower {
	var url = "jdbc:oracle:thin:@"
	var path = ""
	var id = ""
	var pass = ""
	var con : Connection? = null
	var paths : ArrayList<String> = ArrayList()
	constructor(){
		val file = File("path.txt")
		val reader = BufferedReader(FileReader(file))
		while(true){
			var line = reader.readLine() ?:break
			line = line.substring(line.indexOf("=")+1, line.length).trim()
			println(line)	//검증
			paths.add(line)
		}
		url = url + paths[0] + ":JAVA"
		path = paths[1]
		id = paths[2]
		pass = paths[3]
	}

	fun start(){
		Class.forName("oracle.jdbc.driver.OracleDriver");
		con = DriverManager.getConnection(url,id,pass);
	}
}
fun main() {
	System.setProperty("file.encoding", "UTF-8")
	try {
		val charset = Charset::class.java.getDeclaredField("defaultCharset")
		charset.isAccessible = true
		charset.set(null, null)
	} catch (e: Exception) {
	}
	var tower = Tower()
	tower.start()
	Login(tower)
}