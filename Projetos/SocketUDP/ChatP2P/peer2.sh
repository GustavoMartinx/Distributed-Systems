SERVER=src.UDPServer
PORT_ORIGIN=5555
PORT_DESTIN=6666
IP_ADDRESS=127.0.0.1
NICKNAME=server2

echo "Running $NICKNAME in $IP_ADDRESS:$PORT_ORIGIN"
java $SERVER $PORT_ORIGIN $PORT_DESTIN $NICKNAME $IP_ADDRESS