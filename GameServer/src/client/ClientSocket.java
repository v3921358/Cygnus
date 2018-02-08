/*
 * Copyright (C) 2018 Kaz Voeten
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package client;

import io.netty.channel.Channel;
import io.netty.util.concurrent.ScheduledFuture;
import net.InPacket;

import net.Socket;
import util.HexUtils;

/**
 *
 * @author Kaz Voeten
 */
public class ClientSocket extends Socket {

    public ScheduledFuture<?> PingTask;
    //public HashMap<Integer, User> mUsers = new HashMap<>();

    public ClientSocket(Channel channel, int uSeqSend, int uSeqRcv) {
        super(channel, uSeqSend, uSeqRcv);
    }
    
    public void ProcessPacket(InPacket iPacket) {
        int nPacketID = iPacket.DecodeShort();
        switch (nPacketID) {
            default:
                System.out.println("[DEBUG] Received unhandled Client packet. nPacketID: " 
                        + nPacketID + ". Data: " 
                        + HexUtils.ToHex(iPacket.Decode(iPacket.GetRemainder())));
        }
    }
}