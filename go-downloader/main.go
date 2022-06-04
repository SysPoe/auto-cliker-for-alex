package main
import (
	"net/http";
	"os";
	"io";
	"os/exec";
	"runtime";
)

func main() {
	resp, err := http.Get("http://github.com/SysPoe/auto-cliker-for-alex/releases/download/0.0.1/auto-clicker-0.0.1alpha.jar")
	if err != nil {
		panic(err)
}
	defer resp.Body.Close()
	out, err := os.Create("clicker.jar")
	if err != nil {
		panic(err)
	}
	defer out.Close()
	io.Copy(out, resp.Body)
	if runtime.GOOS == "windows" {
		cmd := exec.Command("cmd.exe", "/C", "java", "-jar", "clicker.jar");
		err := cmd.Run()
		if err != nil {
			panic(err)
		}
		return
	}
	if runtime.GOOS != "windows" {
		cmd := exec.Command("lxterminal", "-e", "java", "-jar", "clicker.jar")
		err := cmd.Run()
		if err != nil {
			panic(err)
		}
	}
}