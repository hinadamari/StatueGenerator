StatueGenerator
===============

CraftBukkit Plugin  
プレイヤーのスキン画像をベースとしたブロックアートを生成します  
[こちら](https://github.com/syamn/ArtGenerator)の処理を参考に作成しています  
ライセンスはGPLv3です

##### コマンド
**/statuegenerator reload**  
config.ymlを再読み込みします  
**/statuegenerator generator <PlayerID>**  
指定したPlayerIDのブロックアートを生成します  
（エイリアス：statuegenerator->statuegen, sg, statue generator->gen）

##### 設定ファイル(config.yml)の主な項目
**skin-url**  
スキン画像を取得するためのURLです。コマンド使用時、%player%を入力したPlayerIDに置き換えます  
**allowed-blocks**  
ブロックアート生成に使われるブロックID群を指定します  
IDとブロックの対応については[こちら](http://ja.minecraftwiki.net/wiki/Data_values)を参考にして下さい  
初期設定で書かれているIDに加えて岩盤(7)、ダブルハーフブロック(43:8,43:9)、
かぼちゃ(86)、ジャック・オ・ランタン(91)も指定することができます

##### 権限
**statuegenerator.reload**  
reloadコマンドを使用できるようになります(デフォでOP)  
**statuegenerator.generate**  
generatorコマンドを使用できるようになります(デフォでOP)
