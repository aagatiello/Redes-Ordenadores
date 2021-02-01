library IEEE;
use IEEE.STD_LOGIC_1164.all;

entity tb_dec9_5 is
end tb_dec9_5;

architecture Behavioral of tb_dec9_5 is
	component dec9_5 is
		port (
			I            : in std_logic_vector(8 downto 0);
			Y            : out std_logic_vector(4 downto 0);
			error_signal : out std_logic_vector(3 downto 0)
		);
	end component;

	signal Y            : std_logic_vector(4 downto 0);
	signal I            : std_logic_vector(8 downto 0);
	signal error_signal : std_logic_vector(3 downto 0);
begin
	DUT : dec9_5 port map(I, Y, error_signal);
	
	process begin
		I <= "000100100";
		wait for 10ns;
	end process;
	
end Behavioral;