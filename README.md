# step2-BE-kakao-shop
카카오 테크 캠퍼스 2단계 카카오 쇼핑하기 백엔드 클론 프로젝트 레포지토리입니다.

# 1주차

카카오 테크 캠퍼스 2단계 - BE - 1주차 클론 과제
</br>
</br>

## **과제명**
```
1. 요구사항분석/API요청 및 응답 시나리오 분석
2. 요구사항 추가 반영 및 테이블 설계도
```

## **과제 설명**
```
1. 요구사항 시나리오를 보고 부족해 보이는 기능을 하나 이상 체크하여 README에 내용을 작성하시오.
2. 제시된 화면설계를 보고 해당 화면설계와 배포된 기존 서버의 API주소를 매칭하여 README에 내용을 작성하시오.
3. 배포된 서버에 모든 API를 POSTMAN으로 요청해본 뒤 응답되는 데이터를 확인하고 부족한 데이터가 무엇인지 체크하여 README에 내용을 작성하시오.
4. 테이블 설계를 하여 README에 ER-Diagram을 추가하여 제출하시오. 
```

## **과제 1**
```
API에 명세된 기능과 화면  이외에, 만약 내가 유저라면 서비스를 이용했을 때의 다양한 경우의 수를 생각해보고,
   또 내가 서비스 제공자라면 서비스를 관리할 때 필요한 다양한 경우의 수를 생각해봄으로써 부족한 기능등을 예상해볼 수 있었다.

(1) API 문서에는 단지 상품 목록들을 조회할 수 있는 API만 있는데, 상품을 등록하고 삭제하는 API가 필요하다. (for 판매자)

(2) 옵션 또한 조회만 가능한데, 옵션을 등록하고 삭제하는 API가 필요하다. (for 판매자)

(3) 장바구니를 한 번 생성 했으면, 이를 사용자가 초기화 할 수 없는 문제가 있다(제공된 웹 페이지를 구동해보니..).
   이를 해결하기 위해 장바구니를 삭제하는 버튼과, 삭제하는 API가 필요하다. (for 사용자)

(4) 주문을 했을 때 단지 조회만 가능한데.. 주문을 취소하고 싶을 때 이를 사용자가 할 수 없는 문제가 있어서, 주문을 삭제하는 기능이 필요하다.
   그래서 주문을 삭제하는 버튼과, 주문을 삭제하는 API가 필요하다. (for 사용자)

또 "서비스가 가능한" 쇼핑몰을 위해 추가해야할 기능들을 생각해보면(거시적으로)
(5) '톡딜가 구매' 기능 구현 - 장바구니를 거치지 않고 개별 상품을 구매할 수 있는 기능이 구현되어야 한다. (구현 방법은 장바구니를 거치는 방법과 유사)

(6) '결제' 기능 구현 - 주문을 완료하기 위해선 '결제'가 완료 되어야 한다. 이때 중요한 것은 보안이고 이를 위해서 이니시스와 같은 결제 플랫폼을 이용해야한다.
                  이를 위해서 고객이 결제버튼을 누르면 결제 플랫폼의 API를 통해 결제가 진행되고, 완료되면 성공값을 리턴 받도록 기능이 구현되야한다.

(7) '배달' 기능 구현 - 쇼핑몰 서비스 제공자는 주문 완료가 된 주문 데이터를 보고 제품을 고객에게 배송해야 한다.
                  배송은 물류 플랫폼을 통해 제품이 배송 되어야 하는데, 이를 위해 고객의 주소와 제품 데이터를 물류 플랫폼에 전달하는 기능이 필요하다.

(8) '검색' 기능 구현 - 고객은 웹 화면에 제시된 상품 말고 직접 검색을 통해 상품을 찾는 경우도 많다.
           이를 위해 개별 상품을 검색하는 검색 기능이 구현되어야 한다.

(9) '간편 가입' 기능 구현 - 고객 편의를 위한 기능인데, 고객의  네이버나 카카오 계정을 통해 회원 가입을 할 수 있는 기능이다.
               이를 위해서 네이버 혹 카카오 API에 고객이 계정 정보를 입력해서 넘겨주면, API로부터 가입을 위한 정보를 받아오는 기능을 구현해야 한다. 
```

## **과제 2**
```
(카카오 화면설계 시나리오가 있음)
-> 사용자라고 가정하고, 주문을 완료하는 시나리오 순으로 서술해보면

(1)http://localhost:3000
-> 사용자가 서비스 홈페이지 첫화면에 접속하면, 전체 목록을 조회하는 API가 호출되어 'product_table'의 데이터들이 불러와진다
   이때 특정 상품을 클릭하면, 개별 상품을 조회하는 API가 호출되고, 'option_table'의 데이터들이 호출된다. 

(2)http://localhost:3000/products/1
-> 개별 상품 상세 조회 페이지로, 이 페이지에선 우선 불러온 옵션을 고를 수 있고, 다음으로 고른 옵션을 '장바구니 담기' 할 수 있다.
   이때 '장바구니 담기' 버튼을 클릭하면, 장바구니를 생성하는 API가 호출되고, 'cart_table'에 데이터가 생성된다.  

(3)http://localhost:3000/cart
-> 메인 페이지에서 장바구니 모양 버튼을 클릭하면 장바구니를 호출하는 API가 호출되고, 'cart_table'의 데이터들이 불러와지고, 이들이 보여진다.
   그리고 이 cart 데이터들은 수정이 가능하다. 이때 '주문하기' 버튼을 클릭하면, (결제 전) 주문 내역을 생성하는 API가 호출되고 주문 페이지로 넘어간다.
   이때 확정된 'cart_table'의 데이터들이 'order_item_table'로 넘어온다. 
+@) 결제 전 주문 내역과 결제 후 주문 내역은 엄격히 구분되어야 하는 데이터들이다.

(4)http://localhost:3000/order
-> '주문하기' 버튼을 클릭했을 때 넘어온 페이지로, 'oder_item_table'의 데이터들이 불러와지고, 이들이 보여진다.
   이때 '결제하기' 버튼을 누르면 (결제 후)주문 내역을 생성하는(주문 인서트) API가 호출되고(API 문서에 있는),
   'order_item_table'에 있는 데이터들이 'order_table'로 넘어온다.
   이 데이터들이 진짜 주문 데이터들로, 서비스 제공자는 이 데이터들을 보고 제품을 보내줘야한다.

(5)http://localhost:3000/oreders/1
-> 'order_table'의 데이터들이 불러와지고, 이들이 보여진다. (결제까지 끝난 확정 주문 데이터)
+@) 1번에서 언급한 주문을 삭제하는 API 이때 생성된 'order_table'을 삭제하는 API다.
```

## **과제 3**
```
기존에 제시된 API 명세 기능들을 POSTMAN을 통해 방법으로 사용해보았는데.. 명세에 제시된 기능들을 구현할 때 부족한 데이터는 없었다(API 문서에 제시된 데이터들) .
그래서 문제 1에서 제시된 추가 기능 구현을 위한 데이터를 서술해보겠다.

(1) 관리자 ID, PASSWORD - 서비스 제공자와 서비스 유저의 ID는 엄격히 구분되어야 하며, 별도의 테이블에서 관리되어야 한다.
              관리자는 관리자 ID를 통해 위에서 서술한 옵션 등록, 옵션 삭제, 상품 등록, 상품 삭제를 할 수 있다.

(2) 주문 시간 - 여러가지 이유로 'order_table' 'order_item_table'에 주문 시간을 기록해야한다. 
         우선 결제 플랫폼 API에 데이터를 넘겨줄 때, 이상 결제를 방지하기 위해 주문 시간을 넘겨줘야 하고.
         또 추후에 고객 서비스에 사용할 데이터로써 주문 시간 데이터가 필요하다. (마케팅, 클레임 대응 등등..)  

(3) 배달 주소, 요청사항 - 배달 기능을 구현하기 위해선 'order_table'에 고객의 주소가 있어야 한다.
             이 주소와 배달시 요청 사항을 물류 플랫폼 API에 넘겨준다.

(4) 배송비 - 판매자는 무료 배송을 할 수 있지만, 유료 배송도 할 수 있다. 이 금액을 포함해서 결제가 진행되어야 하기 때문에,
       cart 단계부터 배송비를 추가하여 전체 금액을 계산하고, 이를 결제 플랫폼의 API에 넘겨줘야 한다. 

위 데이터들도 추가하면 프론트엔드 화면에 모두 반영될 수 있다! 
```

## **과제 4**
```
(5번을 하고 이 테이블을 바탕으로 ER-Diagram을 만드는 방법을 사용했습니다)
+-------------------+       +---------------------+
|       member      |       |       product       |
+-------------------+       +---------------------+
| member_id (PK)    |       | product_id (PK)     |
| username          |       | product_name        |
| email             |       | image               |
| password          |       | product_price       |
+-------------------+       | description         |
                            | starCount           |
                            +---------------------+
                                    |
                                    |
                                    |
                                    |
                                    |
                              +--------------+
                              |    option    |
                              +--------------+
                              | option_id (PK)|
                              | option_name   |
                              | option_price  |
                              +--------------+
                                    |
                                    |
                                    |
                                    |
                                    |
                            +--------------+
                            |     cart     |
                            +--------------+
                            | cart_id (PK) |
                            | member_id (FK)|
                            | product_id (FK)|
                            | option_id (FK)|
                            | quantity     |
                            | partial_sum_price|
                            | order_date   |
                            | total_price  |
                            +--------------+
                                    |
                                    |
                                    |
                                    |
                                    |
                            +-----------------+
                            |    order_item   |
                            +-----------------+
                            | order_item_id (PK)|
                            | cart_id (FK)  |
                            | product_id (FK)|
                            | option_id (FK)|
                            | quantity     |
                            | order_date   |
                            | total_price  |
                            +-----------------+
                                    |
                                    |
                                    |
                                    |
                                    |
                            +---------------+
                            |      order    |
                            +---------------+
                            | order_id (PK)|
                            | member_id (FK)|
                            | product_id (FK)|
                            | option_id (FK)|
                            | quantity     |
                            | order_date   |
                            | total_price  |
                            +---------------+
```

## **과제 5**
```
pdf 데이터 테이블 채우기 과제
------------------------------------------
(1) member_table
member_id (Primary Key): 회원 식별자
username: 회원 이름
email: 회원 이메일
password: 회원 비밀번호
-------------------------------------------
(2) product_table
product_id (Primary Key): 상품 식별자
product_name: 상품 이름
image : 상품 이미지
product_price: 상품 가격
description: 상품 설명
starCount : 별점
option_id (Foreign Key): 옵션 식별자
option_table // 각 상품은 고유의 option_table을 가진다. 
-------------------------------------------
(3) option_table
option_id (Primary Key): 옵션 식별자
option_name: 옵션 이름
option_price: 옵션 가격
-------------------------------------------
(4) cart_table
cart_id (Primary Key): 장바구니 식별자
user_id(Foreign Key): 유저 식별자
option_id (Foreign Key): 옵션 식별자
quantity: 상품 수량
total_price: 주문 총액
------------------------------------------- cart_tabled에서 데이터가 거의 비슷하게 넘어오는데 partial_sum_price가 빠졌다(total 계산 후 필요가 없음)
(5) order_item_table
order_item_id (Primary Key): 주문 식별자
product_id (Foreign Key): 상품 식별자
product_name : 상품 이름
option_id (Foreign Key): 옵션 식별자
option_table
quantity: 상품 수량
order_date: 주문 일자
total_price: 주문 총액
------------------------------------------- order_table은 결제 후 그대로 order_table로 
(6) order_table
order_id (Primary Key): 주문 식별자
product_id (Foreign Key): 상품 식별자
product_name : 상품 이름
option_id (Foreign Key): 옵션 식별자
option_table
quantity: 상품 수량
order_date: 주문 일자
total_price: 주문 총액
```

</br>

## **과제 상세 : 수강생들이 과제를 진행할 때, 유념해야할 것**
아래 항목은 반드시 포함하여 과제 수행해주세요!
>- 부족한 기능에 대한 요구사항을 미리 예상할 수 있는가? (예를 들면 상품등록 api가 기존 요구사항에는 없는데 추후 필요하지는 않을지, 이런 부분들을 생각하였는지) 
>- 요구사항에 맞는 API를 분석하고 사용자 시나리오를 설계하였는가? (예를 들어 배포된 서버와 화면 설계를 제시해줄 예정인데, 특정 버튼을 클릭했을 때 어떤 API가 호출되어야 할지를 아는지)
>- 응답되는 데이터가 프론트앤드 화면에 모두 반영될 수 있는지를 체크하였는가?(예를 들어 배송관련 비용이 있는데, 이런것들이 API에는 없는데 이런 부분을 캐치할 수 있는지)
>- 테이블 설계가 모든 API를 만족할 수 있게 나왔는가? (테이블이 효율적으로 나왔는가 보다는 해당 테이블로 요구사항을 만족할 수 있는지에 대한 여부만)
>- 테이블명이 이해하기 쉽게 만들어졌는가? (상품테이블이 product이면 이해하기 쉽지만, material이라고 하면 이해하기 어렵기 때문)

</br>

## **코드리뷰 관련: PR시, 아래 내용을 포함하여 코멘트 남겨주세요.**
**1. PR 제목과 내용을 아래와 같이 작성 해주세요.**

>- PR 제목 : 부산대BE_라이언_1주차 과제

</br>

**2. PR 내용 :**

>- 코드 작성하면서 어려웠던 점
>- 코드 리뷰 시, 멘토님이 중점적으로 리뷰해줬으면 하는 부분

# 2주차

카카오 테크 캠퍼스 2단계 - BE - 2주차 클론 과제
</br>
</br>

## **과제명**
```
1. 전체 API 주소 설계
2. Mock API Controller 구현
```

## **과제 설명**
```
1. API주소를 설계하여 README에 내용을 작성하시오.
2. 가짜 데이터를 설계하여 응답하는 스프링부트 컨트롤러를 작성하고 소스코드를 업로드하시오.
```

## **과제 1**
```
초반에 API 문서를 수정해서 설계를 요구하는 것이 의도인지 햇갈렸었다. 

그러나 합의된 API 문서는 수정하지 않는 것이 원칙이기 때문에 1주차 자료인 'API 문서'를 거의 그대로 참조하였다.   


(1) 전체 상품 목록 조회
>- HTTP 메서드 : GET
>- 주소 : /products

(2) 개별 상품 상세 조회
>- HTTP 메서드 : GET
<<<<<<< HEAD
>- 주소 : /products/{id}

(3) 이메일 중복 체크
>- HTTP 메서드 : POST
>- 주소 : /check

(4) 회원가입
>- HTTP 메서드 : POST
>- 주소 : /join

(5) 로그인
>- HTTP 메서드 : POST
>- 주소 : /login

(6) 장바구니 담기
>- HTTP 메서드 : POST 
>- 주소 : /cart/add

(7) 장바구니 조회
>- HTTP 메서드 : GET
>- 주소 : /carts

(8) 주문하기(장바구니 수정)
>- HTTP 메서드 : POST 
>- 주소 : /carts/update

(9) 결제하기
>- HTTP 메서드 : POST
>- 주소 : /orders/save

(9) 주문 결과 확인
>- HTTP 메서드 : GET
>- 주소 : /orders/{id}

```

## **과제 2**
```

가짜 데이터를 가지고 응답하는 controller를 만들어야 하고, 이 controller에서 사용 할 DTO를 설계해야 한다. 
Postman에서 API 주소에 대해 GET 메서드를 실행했을 때, API 문서에 제시된 response 값과 같은 형식이 나오도록 만들면 된다!
(cart, product의 경우 구현되어 있고, order를 구현하면 된다)

Mock API 구현을 위한 controller의 가짜 데이터의 경우, API 문서에 제시된 데이터를 사용했다.
또 order의 DTO 이름을 설계할 때 cart, product의 DTO 이름과 일관성 있도록 이름을 지었고, 
cart, product, order가 동일한 코드의 DTO가 필요하더라도, 이를 공유하지 않고 분리시켜서 DTO를 구성하였다.

코드는 2_week_hw 파일에 첨부하였다.  

```

</br>

## **과제 상세 : 수강생들이 과제를 진행할 때, 유념해야할 것**
아래 항목은 반드시 포함하여 과제 수행해주세요!
>- User 도메인을 제외한 전체 API 주소 설계가 RestAPI 맞게 설계되었는가? POST와 GET으로만 구현되어 있어도 됨.
>- 가짜 데이터를 설계하여 Mock API를 잘 구현하였는가? (예를 들어 DB연결없이 컨트롤러만 만들어서 배포된 서버의 응답과 동일한 형태로 데이터가 응답되는지 여부)
>- DTO에 타입은 올바르게 지정되었는가?
>- DTO에 이름은 일관성이 있는가? (예를 들어 어떤 것은 JoinDTO, 어떤 것은 joinDto, 어떤 것은 DtoJoin 이런식으로 되어 있으면 일관성이 없는것이다)
>- DTO를 공유해서 쓰면 안된다 (동일한 데이터가 응답된다 하더라도, 화면은 수시로 변경될 수 있기 때문에 DTO를 공유하고 있으면 배점을 받지 못함)

</br>

## **코드리뷰 관련: PR시, 아래 내용을 포함하여 코멘트 남겨주세요.**
**1. PR 제목과 내용을 아래와 같이 작성 해주세요.**

>- PR 제목 : 부산대BE_라이언_2주차 과제

</br>

**2. PR 내용 :**

>- 코드 작성하면서 어려웠던 점
>- 코드 리뷰 시, 멘토님이 중점적으로 리뷰해줬으면 하는 부분

# 3주차

카카오 테크 캠퍼스 2단계 - BE - 3주차 클론 과제
</br>
</br>

## **과제명**
```
1. 레포지토리 단위테스트
```

## **과제 설명**
```
1. 레포지토리 단위테스트를 구현하여 소스코드를 제출하시오.
2. 쿼리를 테스트하면서 가장 좋은 쿼리를 작성해보시오.
```

</br>

## **과제 상세 : 수강생들이 과제를 진행할 때, 유념해야할 것**
아래 항목은 반드시 포함하여 과제 수행해주세요!
>- 레포지토리 단위테스트가 구현되었는가?
>- 테스트 메서드끼리 유기적으로 연결되지 않았는가? (테스트는 격리성이 필요하다)
>- Persistene Context를 clear하여서 테스트가 구현되었는가? (더미데이터를 JPA를 이용해서 insert 할 예정인데, 레포지토리 테스트시에 영속화된 데이터 때문에 쿼리를 제대로 보지 못할 수 있기 때문에)
>- 테스트 코드의 쿼리 관련된 메서드가 너무 많은 select를 유발하지 않는지? (적절한 한방쿼리, 효율적인 in query, N+1 문제 등이 해결된 쿼리)
</br>

## **코드리뷰 관련: PR시, 아래 내용을 포함하여 코멘트 남겨주세요.**
**1. PR 제목과 내용을 아래와 같이 작성 해주세요.**

>- PR 제목 : 부산대BE_라이언_3주차 과제

</br>

**2. PR 내용 :**

>- 코드 작성하면서 어려웠던 점
>- 코드 리뷰 시, 멘토님이 중점적으로 리뷰해줬으면 하는 부분

# 4주차

카카오 테크 캠퍼스 2단계 - BE - 4주차 클론 과제
</br>
</br>

## **과제명**
```
1. 컨트롤러 단위 테스트
```

## **과제 설명**
```
1. 컨트롤러 단위테스트를 작성한뒤 소스코드를 업로드하시오.
2. stub을 구현하시오.
```

</br>

## **과제 상세 : 수강생들이 과제를 진행할 때, 유념해야할 것**
아래 항목은 반드시 포함하여 과제 수행해주세요!
>- 컨트롤러 단위테스트가 구현되었는가?
>- Mockito를 이용하여 stub을 구현하였는가?
>- 인증이 필요한 컨트롤러를 테스트할 수 있는가?
>- 200 ok만 체크한 것은 아닌가? (해당 컨트롤러에서 제일 필요한 데이터에 대한 테스트가 구현되었는가?)
</br>


## **코드리뷰 관련: PR시, 아래 내용을 포함하여 코멘트 남겨주세요.**
**1. PR 제목과 내용을 아래와 같이 작성 해주세요.**

>- PR 제목 : 부산대BE_라이언_4주차 과제 

</br>

**2. PR 내용 :**

>- 코드 작성하면서 어려웠던 점
>- 코드 리뷰 시, 멘토님이 중점적으로 리뷰해줬으면 하는 부분

# 5주차

카카오 테크 캠퍼스 2단계 - BE - 5주차 클론 과제
</br>
</br>

## **과제명**
```
1. 실패 단위 테스트
```

## **과제 설명**
```
1. 컨트롤러 단위테스트를 구현하는데, 실패 테스트 코드를 구현하시오.
2. 어떤 문제가 발생할 수 있을지 모든 시나리오를 생각해본 뒤, 실패에 대한 모든 테스트를 구현하시오.
```

</br>

## **과제 상세 : 수강생들이 과제를 진행할 때, 유념해야할 것**
아래 항목은 반드시 포함하여 과제 수행해주세요!
>- 실패 단위 테스트가 구현되었는가?
>- 모든 예외에 대한 실패 테스트가 구현되었는가?
</br>

## **코드리뷰 관련: PR시, 아래 내용을 포함하여 코멘트 남겨주세요.**
**1. PR 제목과 내용을 아래와 같이 작성 해주세요.**

>- PR 제목 : 부산대BE_라이언_5주차 과제

</br>

**2. PR 내용 :**

>- 코드 작성하면서 어려웠던 점
>- 코드 리뷰 시, 멘토님이 중점적으로 리뷰해줬으면 하는 부분

# 6주차

카카오 테크 캠퍼스 2단계 - BE - 6주차 클론 과제
</br>
</br>

## **과제명**
```
1. 카카오 클라우드 배포
```

## **과제 설명**
```
1. 통합테스트를 구현하시오.
2. API문서를 구현하시오. (swagger, restdoc, word로 직접 작성, 공책에 적어서 제출 등 모든 방법이 다 가능합니다)
3. 프론트앤드에 입장을 생각해본뒤 어떤 문서를 가장 원할지 생각해본뒤 API문서를 작성하시오.
4. 카카오 클라우드에 배포하시오.
5. 배포한 뒤 서비스 장애가 일어날 수 있으니, 해당 장애에 대처할 수 있게 로그를 작성하시오. (로그는 DB에 넣어도 되고, 외부 라이브러리를 사용해도 되고, 파일로 남겨도 된다 - 단 장애 발생시 확인을 할 수 있어야 한다)
```

</br>

## **과제 상세 : 수강생들이 과제를 진행할 때, 유념해야할 것**
아래 항목은 반드시 포함하여 과제 수행해주세요!
>- 통합테스트가 구현되었는가?
>- API문서가 구현되었는가?
>- 배포가 정상적으로 되었는가?
>- 서비스에 문제가 발생했을 때, 로그를 통해 문제를 확인할 수 있는가?
</br>

## **코드리뷰 관련: PR시, 아래 내용을 포함하여 코멘트 남겨주세요.**
**1. PR 제목과 내용을 아래와 같이 작성 해주세요.**

>- PR 제목 : 부산대BE_라이언_6주차 과제

</br>

**2. PR 내용 :**

>- 코드 작성하면서 어려웠던 점
>- 코드 리뷰 시, 멘토님이 중점적으로 리뷰해줬으면 하는 부분
