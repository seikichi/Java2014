全て final にすべき．
final にしておかないと，子クラスでオーバーライドされて
親クラスが提示していた契約が破棄されてしまう．

- Vehicle
  - getID, getSpeed, getAngle, getOwnerName, stop, changeSpeed, turn
- PassengerVehicle
  - getSeatNum, getOccupiedSeatNum, setOccupiedSeatNum
