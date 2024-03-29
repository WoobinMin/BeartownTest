

사용 버전: 최신 spigot 또는 paper (상관 없음)

현재 api 버전: 1.20



이 테스트 프로젝트는 버킷api 사용 경험이 부족하거나 포토폴리오가 없는 개발자 실력 검증을 위함입니다.테스트 프로젝트가 주어진 이후 7일 내로 완수하여 코드를 github에 공유하거나, 공개되기를 원하지 않으시는 경우 private 으로 올린 후 **aaa8881** 로 공유 부탁드립니다.



난이도: **초급** 

```
이 테스트 프로젝트에서는 다음 역량을 체크하는데 관심을 가지고 있습니다.

1. 명령어, gui 등 기초 버킷 api 사용법 숙지에 대한 확인
2. 기초적인 I/O 에 대한 확인
3. 기초 API 작성에 대한 확인
```



이 프로젝트에서는 많은 마인크래프트 서버에서 사용되고 있는 핫타임 기능을 만들어 보려고 합니다.

핫타임 기능이란 특정 시간대에 서버에 접속해 있는 모든 플레이어들에게 일정한 보상을 주는 것을 의미합니다.



**명령어**

필요 권한: **giveaway.use**

```
/giveaway : 핫타임 관련 GUI를 보여줍니다. 

alias: 핫타임
```



필요 권한: **giveaway.admin**

```
/giveawayadmin run : 지금 당장 핫타임을 실행시킵니다.
/giveawayadmin runafter (time_in_seconds) : (time_in_seconds)초 이후 핫타임을 실행시킵니다. 단 해당 명령어가 실행되면 시작 전 일정 시간마다 조금 후에 핫타임이 실행된다는 것을 서버 broadcast 등을 통해 알려 주어야 합니다.
/giveawayadmin cancel: 현재 runafter로 실행되고 있는 핫타임이 있다면 해당 핫타임 진행을 취소시킵니다. 
```



**GUI**

```
핫타임 관련 GUI를 통해 핫타임이 무엇인지에 대한 간략한 설명이 로어에 적힌 버튼과, 얼마만큼의 돈을 지급하는지 확인 할 수 있어야 합니다. 
또한 지금까지 개인별 몇번의 핫타임을 받았는지도 확인해야 하고 총 몇명에게 핫타임 보상이 돌아갔는지도 확인해야 합니다. 
```



**config.yml**

```yaml
reward:
	money: 1000 # vault 와 연동된 돈을 지급합니다.
	
broadcast_message: '핫타임이 시작되어 {player_count}명의 플레이어께서 보상을 획득했습니다!'
individual_message: '{player} 님! 핫타임으로 {money}원을 받았습니다!'
```



**보상이 주어질 때**

```
핫타임 보상은 config.yml 에 설정된 money 값에 해당되는 돈을 지급합니다.

단 다음 조건을 따라야 합니다.
* 계정이 중복되어 접속해 있는 경우- 즉 동일한 IP를 가진 플레이어에게는 중복 보상을 주지 않습니다. 
```



**데이터 저장**

```
유저 데이터는 mysql 을 통해 저장되어야 하고, table 이 없는 경우 새로 알아서 생성해 줘야 합니다.
database 까지 만드는 것은 위험하므로, database 는 "giveaway" 데이터베이스가 있다고 가정하고, 해당 데이터베이스를 사용합니다.
table 명은 자율적으로 만들어 주세요.

1. 핫타임을 지금까지 몇 번이나 받았는지 개인별로 기록합니다. 그리고 이 데이터는 GUI를 통해 확인하도록 합니다. -> DBConnector참고
2. 지금까지 총 몇 명에게 핫타임 보상이 주어졌는지 기록합니다. ( 개인별x, 글로벌 ) -> DBConnector에 getTotalCount, increaseTotalCount
```



**API**

```
GiveawayAPI#getReceived(Player player) : player 이 지금까지 받은 핫타임 개수를 확인합니다.
GiveawayAPI#getTotalReceived : 지금까지 몇번의 핫타임 보상이 주어졌는지 확인합니다.
```


**API**
