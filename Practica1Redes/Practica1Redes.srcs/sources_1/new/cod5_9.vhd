library IEEE;
use IEEE.STD_LOGIC_1164.all;

entity cod5_9 is
	port (
		I : in std_logic_vector(4 downto 0);
		Y : out std_logic_vector(8 downto 0)
	);
end cod5_9;

architecture Dataflow of cod5_9 is
begin
	Y(0) <= I(0);
	Y(1) <= I(0);
	Y(2) <= I(1);
	Y(3) <= I(2);
	Y(4) <= I(3);
	Y(5) <= I(3) xor I(2) xor I(1);
	Y(6) <= I(4);
	Y(7) <= I(4) xor I(2) xor I(1);
	Y(8) <= I(4) xor I(3) xor I(1) xor I(0);
	
end Dataflow;