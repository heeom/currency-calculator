## 요구사항 정리  

### 1. 수취국가 선택시 수취국가/송금국가 환율 표출  
    1. 환율: #,##0.00 KRW/USD    
    2. 수취 금액 : 소숫점 2째자리, 3자리 이상 → ','로 나타냄  
    
### 2. 송금액 USD 입력 후 submit 누르면, 하단에 수취국가 통화로 수취금액이 계산되어 나온다.    
    1. "수취금액은 #,##0.00 KRW 입니다."  

### 3. 잘못된 송금액 입력시 유효성 체크  
    1. 송금액 미입력, 공백 X  
    2. 0 <= 송금액 <= 10,000 USD  
    3. 위와 같은 입력값 입력시 "송금액이 바르지 않습니다." alert  