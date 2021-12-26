# JSON Pathを指定して配列を取得し、その配列内の要素の順序を検証できる

## 文字列の昇順であることを検証できる
* レスポンスボディとしてシナリオデータストアに"{\"tests\": [{\"id\": \"value1\"}, {\"id\": \"value10\"}, {\"id\": \"value2\"}]}"を保存する
* レスポンスのJSONの配列"$.tests"が、文字列"id"の昇順に並んでいる

## 文字列の降順であることを検証できる
* レスポンスボディとしてシナリオデータストアに"{\"tests\": [{\"id\": \"value2\"}, {\"id\": \"value10\"}, {\"id\": \"value1\"}]}"を保存する
* レスポンスのJSONの配列"$.tests"が、文字列"id"の降順に並んでいる

## 数値の昇順であることを検証できる
* レスポンスボディとしてシナリオデータストアに"{\"tests\": [{\"id\": 1}, {\"id\": 2}, {\"id\": 3}]}"を保存する
* レスポンスのJSONの配列"$.tests"が、数値"id"の昇順に並んでいる

## 数値の降順であることを検証できる
* レスポンスボディとしてシナリオデータストアに"{\"tests\": [{\"id\": 3}, {\"id\": 2}, {\"id\": 1}]}"を保存する
* レスポンスのJSONの配列"$.tests"が、数値"id"の降順に並んでいる

## タイムゾーン付きの日付/時間の昇順であることを検証できる
* レスポンスボディとしてシナリオデータストアに"{\"tests\": [{\"createdAt\": \"2007-12-03T10:15:30+01:00\"}, {\"createdAt\": \"2007-12-03T11:15:30+01:00\"}, {\"createdAt\": \"2007-12-05T10:15:30+01:00\"}]}"を保存する
* レスポンスのJSONの配列"$.tests"が、タイムゾーン付きの日付/時間"createdAt"の昇順に並んでいる

## タイムゾーン付きの日付/時間の降順であることを検証できる
* レスポンスボディとしてシナリオデータストアに"{\"tests\": [{\"createdAt\": \"2007-12-06T10:15:30+01:00\"}, {\"createdAt\": \"2007-12-06T09:15:30+01:00\"}, {\"createdAt\": \"2007-12-05T10:15:30+01:00\"}]}"を保存する
* レスポンスのJSONの配列"$.tests"が、タイムゾーン付きの日付/時間"createdAt"の降順に並んでいる
