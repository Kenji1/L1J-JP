/*
 * mapids.sql 作成スクリプト
 */

// Shellオブジェクトを作成
var shell = WScript.CreateObject("WScript.Shell");

// FileSystemオブジェクトを作成
var fso = new ActiveXObject("Scripting.FileSystemObject");

// ファイルオープンオプション定義
var ForReading = 1, ForWriting = 2, ForAppending = 8;
var TristateDefault = -2, TristateTrue = -1, TristateFalse = 0; // System、Unicode、ASCII

// カレントディレクトリを取得
var path = shell.CurrentDirectory;

// zoneファイルを取得
var zoneFile = fso.OpenTextFile(path + "\\zone3-j.tbl", ForReading);

// info.java.txtファイルを取得
var infoFile = fso.OpenTextFile(path + "\\info.java.txt", ForReading);

// mapids.sqlファイルを取得
var sqlFile = fso.OpenTextFile(path + "\\mapids.sql", ForWriting, TristateDefault);

// zoneファイルを読込
var zone = WScript.CreateObject("Scripting.Dictionary");
zoneFile = skipLines(zoneFile, 20);
while(!zoneFile.AtEndOfStream){
	var buff = zoneFile.ReadLine().replace(/'/g, "\\'").split('"');
	var name = buff[1];
	var id = buff[2].split(" ")[2];
	//WScript.Echo(id);
	//WScript.Echo(name);
	if(!zone.Exists(id)){
		zone.Add(id, name);
	}
}

// mapids.sqlファイルを作成
var format = "INSERT INTO `mapids` VALUES({0}, '{1}', {2}, {3}, {4}, {5}, {6}, {7}, {8}, {9}, {10}, {11}, {12}, {13}, {14}, {15}, {16}, {17}, {18}, {19});";
infoFile.SkipLine();
while(!infoFile.AtEndOfStream){
	var data = convert(infoFile.ReadLine()).split(" ");
	//WScript.Echo(data[0]);
	var qs = queryBuilder(format,
		data[0],
		getName(data[0]),
		data[1],
		data[2],
		data[3],
		data[4],
		getMonsterAmount(data[0]),
		getDropRate(data[0]),
		getUniqueRate(data[0]),
		isUnderWater(data[0]),
		canMakable(data[0]),
		canTeleportable(data[0]),
		canEscapable(data[0]),
		canResurrection(data[0]),
		usePainWand(data[0]),
		enablePenalty(data[0]),
		canTakePets(data[0]),
		canRecallPets(data[0]),
		canUsableItem(data[0]),
		canUsableSkill(data[0]));
	sqlFile.WriteLine(qs);
}

// 読込スキップ
function skipLines(file, n){
	for(i = 0; i < n; i++){
		file.SkipLine();
	}
	return file;
}

// 文字列変換
function convert(str) {
	return str.replace(/[,"\{\}]/g, " ").replace(/\s+/g, " ").replace(/^\s+|\s+$/g, "");
}

// クエリ整形
function queryBuilder(format) {
	for (i = 1; i < arguments.length; i++) {
		var reg = new RegExp("\\{" + (i - 1) + "\\}", "g");
		format = format.replace(reg,arguments[i]);
	}
	return format;
}

function getName(id){
	if(zone.Exists(id)){
		return zone.Item(id);
	}else{
		return null;
	}
}

function getMonsterAmount(id){
	switch(id){
	default:
		return 1;
	}
}

function getDropRate(id){
	switch(id){
	default:
		return 1;
	}
}

function getUniqueRate(id){
	switch(id){
	default:
		return 1;
	}
}

function isUnderWater(id){
	switch(id){
	case   "63":
	case   "65":
	case  "253":
	case  "420":
	case  "552":
	case  "555":
	case  "557":
	case  "558":
	case  "604":
	case  "779":
	case "1011":
	case "1012":
	case "1013":
	case "1014":
	case "1015":
	case "1016":
		return 1;
	default:
		return 0;
	}
}

function canMakable(id){
	switch(id){
	case    "0":
	case    "4":
	case  "304":
	case "1000":
	case "1001":
	case "6321":
		return 1;
	default:
		return 0;
	}
}

function canTeleportable(id){
	switch(id){
	case    "0":
	case    "1":
	case    "3":
	case    "4":
	case    "7":
	case    "8":
	case    "9":
	case   "10":
	case   "11":
	case   "12":
	case   "13":
	case   "14":
	case   "15":
	case   "16":
	case   "17":
	case   "18":
	case   "19":
	case   "20":
	case   "22":
	case   "23":
	case   "24":
	case   "25":
	case   "26":
	case   "27":
	case   "28":
	case   "29":
	case   "30":
	case   "31":
	case   "32":
	case   "33":
	case   "34":
	case   "35":
	case   "36":
	case   "43":
	case   "44":
	case   "45":
	case   "46":
	case   "47":
	case   "48":
	case   "49":
	case   "50":
	case   "51":
	case   "52":
	case   "53":
	case   "54":
	case   "55":
	case   "56":
	case   "57":
	case   "58":
	case   "59":
	case   "60":
	case   "61":
	case   "62":
	case   "63":
	case   "64":
	case   "68":
	case   "69":
	case   "72":
	case   "73":
	case   "74":
	case   "75":
	case   "76":
	case   "77":
	case   "85":
	case   "86":
	case  "205":
	case  "206":
	case  "207":
	case  "208":
	case  "300":
	case  "301":
	case  "304":
	case  "305":
	case  "307":
	case  "308":
	case  "309":
	case  "330":
	case  "420":
	case  "440":
	case  "441":
	case  "480":
	case  "482":
	case  "483":
	case  "484":
	case  "502":
	case  "507":
	case  "522":
	case  "523":
	case  "524":
	case  "541":
	case  "542":
	case  "543":
	case  "558":
	case  "600":
	case  "601":
	case  "602":
	case  "603":
	case  "604":
	case  "605":
	case  "606":
	case  "607":
	case  "608":
	case  "610":
	case  "611":
	case  "613":
	case  "620":
	case  "621":
	case  "666":
	case  "702":
	case  "703":
	case  "704":
	case  "705":
	case  "706":
	case  "707":
	case  "708":
	case  "709":
	case  "710":
	case  "711":
	case  "712":
	case  "713":
	case  "714":
	case  "715":
	case  "716":
	case  "717":
	case  "718":
	case  "719":
	case  "720":
	case  "721":
	case  "722":
	case  "723":
	case  "724":
	case  "777":
	case  "778":
	case  "779":
	case  "780":
	case  "783":
	case  "784":
	case "1000":
	case "1001":
	case "2005":
	case "5166":
	case "5167":
	case "5300":
	case "5301":
	case "5302":
	case "5801":
	case "5802":
	case "5803":
	case "5804":
	case "5805":
	case "6041":
	case "6301":
	case "6302":
	case "6303":
	case "6311":
	case "6312":
	case "6313":
	case "6321":
	case "6322":
	case "6323":
	case "7000":
	case "7001":
	case "7002":
	case "7003":
	case "7004":
	case "7005":
	case "7006":
	case "7007":
	case "7008":
	case "7009":
	case "7010":
	case "7011":
	case "7012":
	case "7013":
	case "7014":
	case "7015":
	case "7016":
	case "7017":
	case "7018":
	case "7051":
	case "7052":
	case "7053":
	case "7054":
	case "7055":
	case "7057":
	case "7060":
	case "7061":
	case "7062":
	case "7063":
	case "7064":
	case "7065":
	case "7066":
	case "7067":
	case "7068":
	case "7069":
	case "7070":
	case "7071":
	case "7072":
	case "7073":
	case "7074":
	case "7075":
	case "7075":
	case "7076":
	case "7077":
	case "7078":
	case "7079":
	case "7080":
	case "7100":
	case "7101":
	case "7102":
	case "7103":
	case "7104":
	case "7105":
	case "7106":
	case "7107":
	case "7108":
	case "7109":
	case "8104":
	case "8105":
	case "8106":
	case "8116":
	case "8117":
	case "8118":
	case "9100":
	case "9990":
	case "9991":
	case "9992":
	case "9993":
	case "9994":
	case "9995":
	case "9996":
	case "9997":
	case "9998":
	case "9999":
		return 1;
	default:
		return 0;
	}
}


function canEscapable(id){
	switch(id){
	case    "34":
	case    "37":
	case    "38":
	case    "39":
	case    "40":
	case    "41":
	case    "42":
	case    "65":
	case    "67":
	case    "70":
	case    "90":
	case    "99":
	case   "200":
	case   "303":
	case   "666":
	case   "997":
	case   "998":
	case  "4301":
	case  "4831":
	case  "5124":
	case  "5143":
	case  "5153":
	case  "5302":
	case  "6051":
	case  "6801":
	case  "6901":
	case  "7058":
	case  "7059":
	case  "9000":
	case  "9991":
	case  "9992":
	case  "9993":
	case  "9994":
	case  "9995":
	case  "9997":
	case  "9998":
	case  "9999":
	case "17920":
		return 0;
	default:
		return 1;
	}
}

function canResurrection(id){
	switch(id){
	case    "88":
	case    "89":
	case    "90":
	case    "91":
	case    "92":
	case    "93":
	case    "94":
	case    "95":
	case    "96":
	case    "97":
	case    "98":
	case   "303":
	case   "621":
	case   "997":
	case   "998":
	case  "2005":
	case  "4301":
	case  "4831":
	case  "5143":
	case  "5153":
	case  "5300":
	case  "5301":
	case  "5302":
	case  "6051":
	case  "6801":
	case  "6901":
	case  "9000":
	case "17920":
		return 0;
	default:
		return 1;
	}
}


function usePainWand(id){
	switch(id){
	case     "5":
	case     "6":
	case    "38":
	case    "39":
	case    "40":
	case    "41":
	case    "42":
	case    "57":
	case    "58":
	case    "83":
	case    "84":
	case    "85":
	case    "86":
	case    "99":
	case   "100":
	case   "205":
	case   "206":
	case   "207":
	case   "208":
	case   "340":
	case   "350":
	case   "360":
	case   "370":
	case   "446":
	case   "447":
	case   "518":
	case   "613":
	case   "620":
	case   "621":
	case   "630":
	case   "631":
	case   "632":
	case   "702":
	case   "703":
	case   "704":
	case   "705":
	case   "706":
	case   "707":
	case   "708":
	case   "709":
	case   "710":
	case   "711":
	case   "712":
	case   "713":
	case   "714":
	case   "715":
	case   "716":
	case   "717":
	case   "718":
	case   "719":
	case   "720":
	case   "721":
	case   "722":
	case   "723":
	case   "724":
	case   "725":
	case   "726":
	case   "783":
	case   "784":
	case   "997":
	case   "998":
	case  "1002":
	case  "1003":
	case  "1004":
	case  "1005":
	case  "1006":
	case  "1007":
	case  "1008":
	case  "1009":
	case  "1010":
	case  "1011":
	case  "1012":
	case  "1013":
	case  "1014":
	case  "1015":
	case  "1016":
	case  "1017":
	case  "1018":
	case  "1019":
	case  "1020":
	case  "1021":
	case  "1022":
	case  "1023":
	case  "1024":
	case  "1025":
	case  "1026":
	case  "1027":
	case  "1028":
	case  "2005":
	case  "2006":
	case  "4301":
	case  "4831":
	case  "5124":
	case  "5143":
	case  "5144":
	case  "5145":
	case  "5153":
	case  "5200":
	case  "5300":
	case  "5301":
	case  "5302":
	case  "5303":
	case  "5384":
	case  "5435":
	case  "5501":
	case  "6051":
	case  "6321":
	case  "6322":
	case  "6323":
	case  "6801":
	case  "6901":
	case  "7060":
	case  "7061":
	case  "7062":
	case  "7063":
	case  "7064":
	case  "7065":
	case  "7066":
	case  "7067":
	case  "7068":
	case  "7069":
	case  "7070":
	case  "7071":
	case  "7072":
	case  "7073":
	case  "7074":
	case  "7075":
	case  "7076":
	case  "7077":
	case  "7078":
	case  "7079":
	case  "7080":
	case  "7100":
	case  "8011":
	case  "8012":
	case  "8013":
	case  "8014":
	case  "8015":
	case  "9000":
	case  "9101":
	case  "9102":
	case  "9202":
	case  "9990":
	case  "9991":
	case  "9992":
	case  "9993":
	case  "9994":
	case  "9995":
	case  "9996":
	case  "9997":
	case  "9998":
	case  "9999":
	case "16384":
	case "16896":
	case "17408":
	case "17920":
	case "18432":
	case "18944":
	case "19456":
	case "19968":
	case "20480":
	case "20992":
	case "21504":
	case "22016":
	case "22528":
	case "23040":
	case "23552":
	case "24064":
	case "24576":
	case "25088":
		return 0;
	default:
		return 1;
	}
}

function enablePenalty(id){
	switch(id){
	case  "303":
	case  "621":
	case  "997":
	case  "998":
	case "1005":
	case "1006":
	case "1007":
	case "1008":
	case "1009":
	case "1010":
	case "1011":
	case "1012":
	case "1013":
	case "1014":
	case "1015":
	case "1016":
	case "1017":
	case "1018":
	case "1019":
	case "1020":
	case "1021":
	case "1022":
	case "1023":
	case "1024":
	case "1025":
	case "1026":
	case "1027":
	case "1028":	
	case "2005":
	case "4301":
	case "4831":
	case "5143":
	case "5153":
	case "6051":
	case "6801":
	case "6901":
		return 0;
	default:
		return 1;
	}
}

function canTakePets(id){
	switch(id){
	case    "42":
	case    "57":
	case    "58":
	case    "63":
	case    "65":
	case    "91":
	case    "92":
	case    "95":
	case    "98":
	case    "99":
	case   "100":
	case   "253":
	case   "340":
	case   "350":
	case   "360":
	case   "370":
	case   "420":
	case   "518":
	case   "552":
	case   "555":
	case   "557":
	case   "558":
	case   "604":
	case   "613":
	case   "620":
	case   "621":
	case   "702":
	case   "703":
	case   "704":
	case   "705":
	case   "706":
	case   "707":
	case   "708":
	case   "709":
	case   "710":
	case   "711":
	case   "712":
	case   "713":
	case   "714":
	case   "715":
	case   "716":
	case   "717":
	case   "718":
	case   "719":
	case   "720":
	case   "721":
	case   "722":
	case   "723":
	case   "724":
	case   "725":
	case   "726":
	case   "783":
	case   "784":
	case  "1002":
	case  "1003":
	case  "1004":
	case  "1005":
	case  "1006":
	case  "1007":
	case  "1008":
	case  "1009":
	case  "1010":
	case  "1011":
	case  "1012":
	case  "1013":
	case  "1014":
	case  "1015":
	case  "1016":
	case  "1017":
	case  "1018":
	case  "1019":
	case  "1020":
	case  "1021":
	case  "1022":
	case  "1023":
	case  "1024":
	case  "1025":
	case  "1026":
	case  "1027":
	case  "1028":
	case  "2005":
	case  "2006":
	case  "4301":
	case  "4831":
	case  "5143":
	case  "5144":
	case  "5145":
	case  "5153":
	case  "5200":
	case  "5300":
	case  "5301":
	case  "5302":
	case  "5303":
	case  "5384":
	case  "5435":
	case  "5501":
	case  "6051":
	case  "6801":
	case  "6901":
	case  "7060":
	case  "7061":
	case  "7062":
	case  "7063":
	case  "7064":
	case  "7065":
	case  "7066":
	case  "7067":
	case  "7068":
	case  "7069":
	case  "7070":
	case  "7071":
	case  "7072":
	case  "7073":
	case  "7074":
	case  "7075":
	case  "7076":
	case  "7077":
	case  "7078":
	case  "7079":
	case  "7080":
	case  "7100":
	case  "8011":
	case  "8012":
	case  "8013":
	case  "8014":
	case  "8015":
	case  "9000":
	case  "9101":
	case  "9102":
	case  "9202":
	case  "9991":
	case  "9992":
	case  "9993":
	case  "9994":
	case  "9995":
	case  "9998":
	case  "9999":
	case "16384":
	case "16895":
	case "17408":
	case "17920":
	case "18432":
	case "18944":
	case "19456":
	case "19968":
	case "20480":
	case "20992":
	case "21504":
	case "22016":
	case "22528":
	case "23040":
	case "23552":
	case "24064":
	case "24576":
	case "25088":
		return 0;
	default:
		return 1;
	}
}


function canRecallPets(id){
	switch(id){
	case    "42":
	case    "57":
	case    "58":
	case    "63":
	case    "65":
	case    "88":
	case    "89":
	case    "90":
	case    "91":
	case    "92":
	case    "93":
	case    "94":
	case    "95":
	case    "96":
	case    "97":
	case    "98":
	case    "99":
	case   "100":
	case   "253":
	case   "340":
	case   "350":
	case   "360":
	case   "370":
	case   "420":
	case   "518":
	case   "552":
	case   "555":
	case   "557":
	case   "558":
	case   "604":
	case   "613":
	case   "620":
	case   "702":
	case   "703":
	case   "704":
	case   "705":
	case   "706":
	case   "707":
	case   "708":
	case   "709":
	case   "710":
	case   "711":
	case   "712":
	case   "713":
	case   "714":
	case   "715":
	case   "716":
	case   "717":
	case   "718":
	case   "719":
	case   "720":
	case   "721":
	case   "722":
	case   "723":
	case   "724":
	case   "725":
	case   "726":
	case   "783":
	case   "784":
	case  "1002":
	case  "1003":
	case  "1004":
	case  "1005":
	case  "1006":
	case  "1007":
	case  "1008":
	case  "1009":
	case  "1010":
	case  "1011":
	case  "1012":
	case  "1013":
	case  "1014":
	case  "1015":
	case  "1016":
	case  "1017":
	case  "1018":
	case  "1019":
	case  "1020":
	case  "1021":
	case  "1022":
	case  "1023":
	case  "1024":
	case  "1025":
	case  "1026":
	case  "1027":
	case  "1028":
	case  "2005":
	case  "2006":
	case  "4301":
	case  "4831":
	case  "5143":
	case  "5144":
	case  "5145":
	case  "5153":
	case  "5200":
	case  "5300":
	case  "5301":
	case  "5302":
	case  "5303":
	case  "5384":
	case  "5435":
	case  "5501":
	case  "6051":
	case  "6801":
	case  "6901":
	case  "7060":
	case  "7061":
	case  "7062":
	case  "7063":
	case  "7064":
	case  "7065":
	case  "7066":
	case  "7067":
	case  "7068":
	case  "7069":
	case  "7070":
	case  "7071":
	case  "7072":
	case  "7073":
	case  "7074":
	case  "7075":
	case  "7076":
	case  "7077":
	case  "7078":
	case  "7079":
	case  "7080":
	case  "7100":
	case  "8011":
	case  "8012":
	case  "8013":
	case  "8014":
	case  "8015":
	case  "9000":
	case  "9101":
	case  "9102":
	case  "9202":
	case  "9990":
	case  "9991":
	case  "9992":
	case  "9993":
	case  "9994":
	case  "9995":
	case  "9996":
	case  "9997":
	case  "9998":
	case  "9999":
	case "16384":
	case "16896":
	case "17408":
	case "17920":
	case "18432":
	case "18944":
	case "19456":
	case "19968":
	case "20480":
	case "20992":
	case "21504":
	case "22016":
	case "22528":
	case "23040":
	case "23552":
	case "24064":
	case "24576":
	case "25088":
		return 0;
	default:
		return 1;
	}
}

function canUsableItem(id){
	switch(id){
	case "5131":
	case "5132":
	case "5133":
	case "5134":
	case "5140":
	case "5141":
	case "5142":
	case "5143":
	case "5125":
		return 0;
	default:
		return 1;
	}
}

function canUsableSkill(id){
	switch(id){
	case "5125":
	case "5131":
	case "5132":
	case "5133":
	case "5134":
	case "5140":
	case "5141":
	case "5142":
	case "5143":
		return 0;
	default:
		return 1;
	}
}
