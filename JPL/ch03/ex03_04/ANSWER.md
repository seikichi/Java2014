全て `final` にすべき．`final` にしておかないと，子クラスでオーバーライドされて
親クラスがクラスの利用者に提示していた契約が破棄されてしまう可能性がある．

- Vehicle
  - getID, getSpeed, getAngle, getOwnerName, stop, changeSpeed, turn
- PassengerVehicle
  - getSeatNum, getOccupiedSeatNum, setOccupiedSeatNum
