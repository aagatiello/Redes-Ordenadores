library IEEE;
use IEEE.STD_LOGIC_1164.all;

entity tb_cod5_9 is
end tb_cod5_9;

architecture Behavioral of tb_cod5_9 is
	component cod5_9 is
		port (
			I : in std_logic_vector(4 downto 0);
			Y : out std_logic_vector(8 downto 0)
		);
	end component;

	signal I : std_logic_vector(4 downto 0);
	signal Y : std_logic_vector(8 downto 0);
begin
	DUT : cod5_9 port map(I, Y);

	process begin
		I <= "10010";
		wait for 10ns;
	end process;

end Behavioral;