library IEEE;
use IEEE.STD_LOGIC_1164.all;

entity reception is
    port (
        CLK, RST, X : in std_logic;
        Y           : buffer std_logic
    );
end reception;

architecture Behavioral of reception is
    type state_type is (S0, S1, S2, S3, S4, S5, S6, S7, S8, S9, S10, S11, S12, S13, S14, S15);
    signal current_state, next_state : state_type;
begin
    process (CLK) begin
        if (rising_edge(CLK)) then
            if (RST = '1') then
                current_state <= S0;
            else
                current_state <= next_state;
            end if;
        end if;
    end process;

    process (current_state, X) begin
        case current_state is
            when S0 =>
                Y <= '1';
                if (X = '1') then
                    next_state <= S0;
                else
                    next_state <= S1;
                end if;
            when S1 =>
                Y <= '1';
                if (X = '1') then
                    next_state <= S2;
                else
                    next_state <= S1;
                end if;
            when S2 =>
                Y <= '1';
                if (X = '1') then
                    next_state <= S0;
                else
                    next_state <= S3;
                end if;
            when S3 =>
                Y <= '1';
                if (X = '1') then
                    next_state <= S4;
                else
                    next_state <= S10;
                end if;
            when S4 =>
                Y <= '1';
                if (X = '1') then
                    next_state <= S0;
                else
                    next_state <= S5;
                end if;
            when S5 =>
                Y <= '1';
                if (X = '1') then
                    next_state <= S6;
                else
                    next_state <= S10;
                end if;
            when S6 =>
                Y <= '1';
                if (X = '1') then
                    next_state <= S7;
                else
                    next_state <= S3;
                end if;
            when S7 =>
                Y <= '0';
                if (X = '1') then
                    next_state <= S9;
                else
                    next_state <= S8;
                end if;
            when S8 =>
                Y <= '0';
                if (X = '1') then
                    next_state <= S0;
                else
                    next_state <= S1;
                end if;
            when S9 =>
                Y <= '0';
                if (X = '1') then
                    next_state <= S0;
                else
                    next_state <= S1;
                end if;
            when S10 =>
                Y <= '1';
                if (X = '1') then
                    next_state <= S0;
                else
                    next_state <= S11;
                end if;
            when S11 =>
                Y <= '1';
                if (X = '1') then
                    next_state <= S0;
                else
                    next_state <= S12;
                end if;
            when S12 =>
                Y <= '1';
                if (X = '1') then
                    next_state <= S0;
                else
                    next_state <= S13;
                end if;
            when S13 =>
                Y <= '1';
                if (X = '1') then
                    next_state <= S15;
                else
                    next_state <= S14;
                end if;
            when S14 =>
                Y <= '0';
                if (X = '1') then
                    next_state <= S2;
                else
                    next_state <= S1;
                end if;
            when S15 =>
                Y <= '0';
                if (X = '1') then
                    next_state <= S0;
                else
                    next_state <= S1;
                end if;
        end case;
    end process;

end Behavioral;
