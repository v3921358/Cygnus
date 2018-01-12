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
package wz.etc;

/**
 *
 * @author Kaz Voeten
 */
public class CharacterCard {

    private final short jobid;
    private final int skillid;

    public CharacterCard(short jobid, int skillid) {
        this.jobid = jobid;
        this.skillid = skillid;
    }

    public short getJob() {
        return this.jobid;
    }

    public int getSkill() {
        return this.skillid;
    }
}