PEER=src.PeerUDP

THIS_PORT=5555
PEER_PORT=6666

THIS_IP=127.0.0.1
PEER_IP=127.0.0.1

THIS_NICKNAME=Peer2

echo "Running $THIS_NICKNAME in $THIS_IP:$THIS_PORT"
java $PEER $THIS_PORT $PEER_PORT $THIS_NICKNAME $PEER_IP