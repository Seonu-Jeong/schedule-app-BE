# 스케줄 관리 앱 만들기

## 목차

1. [ERD](#ERD)
2. [API 명세서](#API-명세서)
3. [실행 결과](#실행-결과)

## ERD

![image](https://github.com/user-attachments/assets/c3c61686-ea8d-43cb-8de3-223ae356f64d)

## API 명세서

<!--일정 생성-->
<details>
<summary>일정 생성</summary>

### 기본정보

| 메서드  |      URL       | 인증방식 |
|:----:|:--------------:|:----:|
| POST | /api/schedules | 비밀번호 |

### 요청 바디

|    이름    |  설명  | 필수 |
|:--------:|:----:|:--:|
|  author  | 작성자명 | O  |
|   todo   | 할 일  | O  |
| password | 비밀번호 | O  |

#### json 예시

```
{
    "author" : "정선우",
    "todo" : "청소하기",
    "password" : "abc1234"  
}
```

### 응답

| 상태 코드 |   설명   |
|:-----:|:------:|
|  201  | 정상 등록  |
|  400  | 잘못된 입력 |

</details>

<!--전체 일정 조회-->
<details>
<summary>전체 일정 조회(등록된 일정 불러오기)</summary>

### 기본정보

| 메서드 |      URL       | 인증방식 |
|:---:|:--------------:|:----:|
| GET | /api/schedules |  없음  |

## 요청

### 쿼리 파라미터

|        이름        |  설명  | 필수 |
|:----------------:|:----:|:--:|
|      author      | 작성자명 | O  |
| modificationDate | 수정일  | X  |

#### json 예시
```
{
    "author" : "정선우"
    "modificationDate" : "2024-11-08"
}
```
## 응답

### 본문

|      이름      |     타입     |    설명    |
|:------------:|:----------:|:--------:|
| scheduleList | Schedule[] | 일정 정보 목록 |

### Schedule

|        이름         |   타입   | 설명  |
|:-----------------:|:------:|:---:|
|       todo        | String | 할 일 |
|   creationDate    | String | 작성일 |
| modificaitionDate | String | 수정일 |

#### json 예시
```
[
    {
        "todo" : "청소하기"
        "creationDate" : "2024-11-08 10:00:00"
        "modificationDate" : "2024-11-08 14:00:00"
    },
    {
        "todo" : "숙제하기"
        "creationDate" : "2024-11-07 10:00:00"
        "modificationDate" : "2024-11-10 14:00:00"
    }
]
```

</details>

<!--선택 일정 조회-->
<details>
<summary>선택 일정 조회(선택한 일정 정보 불러오기)</summary>

### 기본정보

| 메서드 |             URL             | 인증방식 |
|:---:|:---------------------------:|:----:|
| GET | /api/schedules/{scheduleId} |  없음  |

## 요청

### 쿼리 파라미터

|     이름     |   설명   | 필수 |
|:----------:|:------:|:--:|
| scheduleId | 일정 아이디 | O  |

## 응답

### 본문

|    이름    |    타입    |  설명   |
|:--------:|:--------:|:-----:|
| schedule | Schedule | 일정 정보 |

### Schedule

|        이름         |   타입   | 설명  |
|:-----------------:|:------:|:---:|
|       todo        | String | 할 일 |
|   creationDate    | String | 작성일 |
| modificaitionDate | String | 수정일 |

#### json 예시
```
{
    "todo" : "청소하기"
    "creationDate" : "2024-11-08 10:00:00"
    "modificationDate" : "2024-11-08 14:00:00"
}
```
</details>

<!--선택 일정 수정-->
<details>
<summary>선택 일정 수정</summary>

### 기본정보

| 메서드 |             URL             | 인증방식 |
|:---:|:---------------------------:|:----:|
| PUT | /api/schedules/{scheduleId} | 비밀번호 |

## 요청

### 쿼리 파라미터

|     이름     |   설명   | 필수 |
|:----------:|:------:|:--:|
| scheduleId | 일정 아이디 | O  |

### 바디

|   이름   |  설명  | 필수 |
|:------:|:----:|:--:|
|  todo  | 할 일  | X  |
| author | 작성자명 | X  |

#### json 예시
```
{
    "todo" : "청소하기"
    "author" : "정선우"
}
```

## 응답

### 본문

| 상태코드 | 설명    |
|:----:|-------|
| 201  | 정상 수정 |
| 400 | 잘못된 입력 |

</details>

<!--선택 일정 삭제-->
<details>
<summary>선택 일정 삭제</summary>

### 기본정보

|  메서드   |             URL             | 인증방식 |
|:------:|:---------------------------:|:----:|
| DELETE | /api/schedules/{scheduleId} | 비밀번호 |

## 요청

### 쿼리 파라미터

|     이름     |   설명   | 필수 |
|:----------:|:------:|:--:|
| scheduleId | 일정 아이디 | O  |

## 응답

### 본문

| 상태코드 | 설명    |
|:----:|-------|
| 200  | 정상 삭제 |
| 400 | 잘못된 입력 |

</details>

## 실행 결과
<details>
<summary>일정 생성</summary>

### 요청1
![image](https://github.com/user-attachments/assets/0ab7139d-fda8-43f6-80aa-1004dcec2bde)

### 응답1
![image](https://github.com/user-attachments/assets/3243f9a8-f37d-4547-a131-8bcd485442a2)

</details>

<details>
<summary>전체 일정 조회</summary>

### 요청1
![image](https://github.com/user-attachments/assets/c18587ed-6364-4998-8508-baeaa6ea9b18)

### 응답1
![image](https://github.com/user-attachments/assets/bd09bb03-6382-411c-aab1-7f01851f6c3c)

### 요청2
![image](https://github.com/user-attachments/assets/4d0ccc9a-a9f6-4f25-8546-2ae76607c747)
### 응답2
![image](https://github.com/user-attachments/assets/8117ef63-ee38-4fbf-9ee4-f2f1f17d1a1b)

</details>

<details>
<summary>선택 일정 조회</summary>

### 요청1
![image](https://github.com/user-attachments/assets/74706606-eb1f-45e2-b683-b6703fc78552)

### 응답1
![image](https://github.com/user-attachments/assets/6fa5edb4-8886-47d3-807f-7dee25488ada)

</details>

<details>
<summary>선택 일정 수정</summary>

### 요청1
![image](https://github.com/user-attachments/assets/1a791ffa-ac0e-498d-a177-3a2bd0aaa9bc)

### 응답1
![image](https://github.com/user-attachments/assets/ece83288-57cc-47d4-8510-c014db194c3e)
![image](https://github.com/user-attachments/assets/4c2d63d4-a494-44bf-aa29-481bd46359ee)

</details>

<details>
<summary>선택 일정 삭제</summary>

### 요청1
![image](https://github.com/user-attachments/assets/206e3f8c-0e34-4d2b-b117-d4cd40a323e1)
![image](https://github.com/user-attachments/assets/9e3a8c74-2742-4a48-b7fc-4513c151ade9)

### 응답1
![image](https://github.com/user-attachments/assets/7eedd78b-4d19-4ad9-b521-32777a317680)
![image](https://github.com/user-attachments/assets/9d7b6f95-6b46-40c4-8823-2b6668ac1239)

</details>