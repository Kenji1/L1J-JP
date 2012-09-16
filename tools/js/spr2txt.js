/*
 * list.sprをlist.txt(中間ファイル)に変換するスクリプト
 */

// FileSystemオブジェクトを作成
var fso = new ActiveXObject("Scripting.FileSystemObject");

// ファイルオープンオプション定義
var ForReading = 1, ForWriting = 2, ForAppending = 8;
var TristateDefault = -2, TristateTrue = -1, TristateFalse = 0; // System、Unicode、ASCII

// カレントディレクトリを取得
var path = "C:\\workspace\\l1j-hack\\trunk\\tools\\spr2txt\\";

// list.sprファイルを取得
var sprFile = fso.OpenTextFile(path + "list_120716.spr", ForReading);

// list.txtファイルを取得
var txtFile = fso.OpenTextFile(path + "list_120716.txt", ForWriting, TristateDefault);

// list.txtファイルを作成
var id = 0;
//sprFile.SkipLine();
while(!sprFile.AtEndOfStream) {
	var result = convert(sprFile.ReadLine());
	txtFile.WriteLine(result);
}

// 変換処理
function convert(str) {
	var result = str.replace(/\^/g, "*").replace(/[a-zA-Z.\(,:\)_\-']/g, " ").replace(/\s+/g, " ").replace(/^\s+|\s+$/g, "").replace(/#/g, "#"+id+" ");
	if(result.indexOf("#") != -1) {
		id++;
	}
	return result;
}
