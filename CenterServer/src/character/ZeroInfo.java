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
package character;

import database.Database;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import net.InPacket;
import net.OutPacket;

/**
 *
 * @author Kaz Voeten
 */
public class ZeroInfo {

    public static final short Flag = -1; //unsigned _int16
    public int nSubHP = 6910;
    public int nSubMP = 100;
    public int nSubSkin = 0;
    public int nSubHair = 37623;
    public int nSubFace = 21290;
    public int nSubMHP = 6910;
    public int nSubMMP = 100;
    public int dbcharZeroLinkCashPart = 0;
    public int nMixBaseHairColor = -1;
    public int nMixAddHairColor = 0;
    public int nMixHairBaseProb = 0;
    public boolean bIsBeta = false;
    public int nLapis = 1562000;
    public int nLazuli = 1572000;

    public ZeroInfo() {
    }

    public void Encode(OutPacket oPacket) {
        if ((Flag & 1) > 0) {
            oPacket.EncodeBool(bIsBeta);
        }
        if ((Flag & 2) > 0) {
            oPacket.EncodeInt(nSubHP);
        }
        if ((Flag & 4) > 0) {
            oPacket.EncodeInt(nSubMP);
        }
        if ((Flag & 8) > 0) {
            oPacket.EncodeByte(nSubSkin);
        }
        if ((Flag & 0x10) > 0) {
            oPacket.EncodeInt(nSubHair);
        }
        if ((Flag & 0x20) > 0) {
            oPacket.EncodeInt(nSubFace);
        }
        if ((Flag & 0x40) > 0) {
            oPacket.EncodeInt(nSubMHP);
        }
        if ((Flag & 0x80) > 0) {
            oPacket.EncodeInt(nSubMMP);
        }
        if ((Flag & 0x100) > 0) {
            oPacket.EncodeInt(dbcharZeroLinkCashPart);
        }
        if ((Flag & 0x200) > 0) {
            oPacket.EncodeInt(nMixBaseHairColor);
            oPacket.EncodeInt(nMixAddHairColor);
            oPacket.EncodeInt(nMixHairBaseProb);
        }
    }

    public static ZeroInfo Decode(InPacket iPacket) {
        ZeroInfo ret = new ZeroInfo();
        if ((Flag & 1) > 0) {
            ret.bIsBeta = iPacket.DecodeBool();
        }
        if ((Flag & 2) > 0) {
            ret.nSubHP = iPacket.DecodeInt();
        }
        if ((Flag & 4) > 0) {
            ret.nSubMP = iPacket.DecodeInt();
        }
        if ((Flag & 8) > 0) {
            ret.nSubSkin = iPacket.DecodeByte();
        }
        if ((Flag & 0x10) > 0) {
            ret.nSubHair = iPacket.DecodeInt();
        }
        if ((Flag & 0x20) > 0) {
            ret.nSubFace = iPacket.DecodeInt();
        }
        if ((Flag & 0x40) > 0) {
            ret.nSubMHP = iPacket.DecodeInt();
        }
        if ((Flag & 0x80) > 0) {
            ret.nSubMMP = iPacket.DecodeInt();
        }
        if ((Flag & 0x100) > 0) {
            ret.dbcharZeroLinkCashPart = iPacket.DecodeInt();
        }
        if ((Flag & 0x200) > 0) {
            ret.nMixBaseHairColor = iPacket.DecodeInt();
            ret.nMixAddHairColor = iPacket.DecodeInt();
            ret.nMixHairBaseProb = iPacket.DecodeInt();
        }
        return ret;
    }

    public void Update(int dwCharacterID) {
        try (Connection c = Database.GetConnection()){
            PreparedStatement ps = c.prepareStatement("UPDATE ZeroInfo SET nSubHP = ?, nSubMP = ?, nSubSkin = ?,"
                    + " nSubHair = ?, nSubFace = ?, nSubMHP = ?, nSubMMP = ?, dbcharZeroLinkCashPart = ?, "
                    + "nMixBaseHairColor = ?, nMixAddHairColor = ?, nMixHairBaseProb = ?, bIsBeta = ?, nLapis = ?, nLazuli = ? "
                    + "WHERE dwCharacterID = ?");
            ps.setInt(1, nSubHP);
            ps.setInt(2, nSubMP);
            ps.setInt(3, nSubSkin);
            ps.setInt(4, nSubHair);
            ps.setInt(5, nSubFace);
            ps.setInt(6, nSubMHP);
            ps.setInt(7, nSubMMP);
            ps.setInt(8, dbcharZeroLinkCashPart);
            ps.setInt(9, nMixBaseHairColor);
            ps.setInt(10, nMixAddHairColor);
            ps.setInt(11, nMixHairBaseProb);
            ps.setBoolean(12, bIsBeta);
            ps.setInt(13, nLapis);
            ps.setInt(14, nLazuli);
            ps.setInt(15, dwCharacterID);
            ps.executeUpdate();
            ps.close();

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void SaveNew(Connection c, int dwCharacterID) {
        try {
            PreparedStatement ps = c.prepareStatement("INSERT INTO ZeroInfo (dwCharacterID, nSubHP, nSubMP, nSubSkin, nSubHair, nSubFace, nSubMHP, nSubMMP, "
                    + "dbcharZeroLinkCashPart, nMixBaseHairColor, nMixAddHairColor, nMixHairBaseProb, bIsBeta, nLapis, nLazuli) "
                    + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
            ps.setInt(1, dwCharacterID);
            ps.setInt(2, nSubHP);
            ps.setInt(3, nSubMP);
            ps.setInt(4, nSubSkin);
            ps.setInt(5, nSubHair);
            ps.setInt(6, nSubFace);
            ps.setInt(7, nSubMHP);
            ps.setInt(8, nSubMMP);
            ps.setInt(9, dbcharZeroLinkCashPart);
            ps.setInt(10, nMixBaseHairColor);
            ps.setInt(11, nMixAddHairColor);
            ps.setInt(12, nMixHairBaseProb);
            ps.setBoolean(13, bIsBeta);
            ps.setInt(14, nLapis);
            ps.setInt(15, nLazuli);
            ps.executeUpdate();
            ps.close();

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void Load(Connection c, int dwCharacterID) {
        try {
            PreparedStatement ps = c.prepareStatement("SELECT * FROM ZeroInfo WHERE dwCharacterID = ?");
            ps.setInt(1, dwCharacterID);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                this.nSubHP = rs.getInt("nSubHP");
                this.nSubMP = rs.getInt("nSubMP");
                this.nSubSkin = rs.getInt("nSubSkin");
                this.nSubHair = rs.getInt("nSubHair");
                this.nSubFace = rs.getInt("nSubFace");
                this.nSubMHP = rs.getInt("nSubMHP");
                this.nSubMMP = rs.getInt("nSubMMP");
                this.dbcharZeroLinkCashPart = rs.getInt("dbcharZeroLinkCashPart");
                this.nMixBaseHairColor = rs.getInt("nMixBaseHairColor");
                this.nMixAddHairColor = rs.getInt("nMixAddHairColor");
                this.nMixHairBaseProb = rs.getInt("nMixHairBaseProb");
                this.bIsBeta = rs.getBoolean("bIsBeta");
                this.nLapis = rs.getInt("nLapis");
                this.nLazuli = rs.getInt("nLazuli");
                ps.executeUpdate();
            }
            ps.close();
            rs.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
