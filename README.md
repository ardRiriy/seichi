#整地鯖 簡易プラグイン
***
##初期設定  
データ保存のためSQLiteを利用しています.  
サーバーを稼働する前に`spigot-x.xx.x.jar`と同階層に,`playerDate.db`を作成する必要があります.  
その後,`create table player(name, uuid, digging)`を実行すると利用可能になります.
***
##利用できるコマンド  
`/grading ranking` - 採掘量ランキング 上位5名を表示します.  
`/grading trash` - ゴミ箱を開きます.このインベントリにアイテムを入れた場合,永久に消滅します.  
`/grading tool` - ツール一式がもらえます.未実装.  
`/grading status`　- 自分の採掘量,および順位を表示します.未実装.rankingに統合するかも.
***
