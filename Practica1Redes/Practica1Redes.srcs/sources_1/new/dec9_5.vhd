library IEEE;
use IEEE.STD_LOGIC_1164.all;

entity dec9_5 is
	port (
		I            : in std_logic_vector(8 downto 0);
		Y            : out std_logic_vector(4 downto 0);
		error_signal : out std_logic_vector(3 downto 0)
	);
end dec9_5;

architecture Structural of dec9_5 is
	signal new_position : std_logic_vector(3 downto 0);
	signal old_position : std_logic_vector(3 downto 0);
begin
	old_position    <= I(8) & I(7) & I(5) & I(1);
	new_position(0) <= I(6) xor I(4) xor I(2) xor I(0) xor I(8);
	new_position(1) <= I(6) xor I(3) xor I(2) xor I(7);
	new_position(2) <= I(4) xor I(3) xor I(2) xor I(5);
	new_position(3) <= I(0) xor I(1);
    error_signal    <= new_position;

	process (I, new_position) begin
		case new_position is
			when "0011" =>
				Y <= not I(6) & I(4) & I(3) & I(2) & I(0);
			when "0101" =>
				Y <= I(6) & not I(4) & I(3) & I(2) & I(0);
			when "0110" =>
				Y <= I(6) & I(4) & not I(3) & I(2) & I(0);
			when "0111" =>
				Y <= I(6) & I(4) & I(3) & not I(2) & I(0);
			when "1001" =>
				Y <= I(6) & I(4) & I(3) & I(2) & not I(0);
			when others =>
				Y <= I(6) & I(4) & I(3) & I(2) & I(0);
		end case;
	end process;

end Structural;