import pygame
from sys import exit
from random import randint, choice

class Player(pygame.sprite.Sprite): # full class for player 
	def __init__(self):
		super().__init__()
		player_walk_1 = pygame.image.load('graphics/player/player_walk_1.png').convert_alpha() # image pictures for walk animation 
		player_walk_2 = pygame.image.load('graphics/player/player_walk_2.png').convert_alpha()
		self.player_walk = [player_walk_1,player_walk_2] # setting up attributes for cyceling between walk animations 
		self.player_index = 0
		self.player_jump = pygame.image.load('graphics/player/jump.png').convert_alpha() # jump animation

		self.image = self.player_walk[self.player_index]
		self.rect = self.image.get_rect(midbottom = (80,300)) # setting rect position to the right and on the ground 
		self.gravity = 0 # gravity set to zero

		self.jump_sound = pygame.mixer.Sound('audio/jump.mp3') # sound for jumping 
		self.jump_sound.set_volume(0.5)

	def player_input(self):
		keys = pygame.key.get_pressed() # if space bar pressed player jumps (gravity is decreased) and sound plays
		if keys[pygame.K_SPACE] and self.rect.bottom >= 300:
			self.gravity = -20
			self.jump_sound.play()

	def apply_gravity(self):
		self.gravity += 1 # gravity increases while player is in air and stops when they hit the ground rect 
		self.rect.y += self.gravity
		if self.rect.bottom >= 300:
			self.rect.bottom = 300

	def animation_state(self):
		if self.rect.bottom < 300: 
			self.image = self.player_jump # player jump animation if player is above ground 
		else:
			self.player_index += 0.1 # walking animation cycles through a [1] index slowly to simulate walking 
			if self.player_index >= len(self.player_walk):self.player_index = 0
			self.image = self.player_walk[int(self.player_index)]

	def update(self): # function to update the player all at once 
		self.player_input()
		self.apply_gravity()
		self.animation_state()

class Obstacle(pygame.sprite.Sprite): # enemy class for snail and fly 
	def __init__(self,type):
		super().__init__()
		
		if type == 'fly': # if fly is on screen 
			fly_1 = pygame.image.load('graphics/fly/fly1.png').convert_alpha() # these 2 images load for flying animation 
			fly_2 = pygame.image.load('graphics/fly/fly2.png').convert_alpha()
			self.frames = [fly_1,fly_2]
			y_pos = 210 # position of fly = in the air 
		else:
			snail_1 = pygame.image.load('graphics/snail/snail1.png').convert_alpha() # if not a fly on screen snail images load
			snail_2 = pygame.image.load('graphics/snail/snail2.png').convert_alpha()
			self.frames = [snail_1,snail_2]
			y_pos  = 300

		self.animation_index = 0
		self.image = self.frames[self.animation_index] # indexs for cycling animations
		self.rect = self.image.get_rect(midbottom = (randint(900,1100),y_pos)) # random y position so enemies appear randomly to the right of the screen

	def animation_state(self): # same as player animation logic, cycles through 2 static images at a relativly slow speed to make it seem like its moving 
		self.animation_index += 0.1 
		if self.animation_index >= len(self.frames): self.animation_index = 0
		self.image = self.frames[int(self.animation_index)]

	def update(self): # updating all enemy sprites at once
		self.animation_state()
		self.rect.x -= 20 # movement speed to the left
		self.destroy()

	def destroy(self): # once an enemy is off screen it is deleated 
		if self.rect.x <= -100: 
			self.kill()

def display_score(): # display the score
	current_time = int(pygame.time.get_ticks() / 1000) - start_time # socre goes up in milliseconds /1000 to make it slower and more readable 
	score_surf = test_font.render(f'Score: {current_time}',False,(64,64,64)) # font for score 
	score_rect = score_surf.get_rect(center = (400,50)) # rect for score
	screen.blit(score_surf,score_rect) # updating the scrore rect and image 
	return current_time # returning current time so it can be used globally 

def collision_sprite():
	if pygame.sprite.spritecollide(player.sprite,obstacle_group,False): # collision for sprites 
		obstacle_group.empty() # empty obstacle group so enemies go back to start when game over screen happens 
		return False
	else: return True

pygame.init()
screen = pygame.display.set_mode((800,400)) # game window 
pygame.display.set_caption('Running Man!')
clock = pygame.time.Clock() # clock to track fps 
test_font = pygame.font.Font('font/Pixeltype.ttf', 50) 
game_active = False # false to have it on start screen first 
start_time = 0 # start time 0 so score is set each run 
score = 0
bg_music = pygame.mixer.Sound('audio/music.wav') # music and volume 
bg_music.play(loops = -1)

#Groups
player = pygame.sprite.GroupSingle() # player class sprite
player.add(Player())

obstacle_group = pygame.sprite.Group() # obstacle group class sprite

sky_surface = pygame.image.load('graphics/Sky.png').convert() #BG surfaces 
ground_surface = pygame.image.load('graphics/ground.png').convert()

# Snail 
snail_frame_1 = pygame.image.load('graphics/snail/snail1.png').convert_alpha() # snail image
snail_frame_2 = pygame.image.load('graphics/snail/snail2.png').convert_alpha()
snail_frames = [snail_frame_1, snail_frame_2]
snail_frame_index = 0                   # snail slither animation logic set up (used in class, not needed here )
snail_surf = snail_frames[snail_frame_index]

# Fly
fly_frame1 = pygame.image.load('graphics/fly/fly1.png').convert_alpha() # fly images 
fly_frame2 = pygame.image.load('graphics/fly/fly2.png').convert_alpha()
fly_frames = [fly_frame1, fly_frame2]
fly_frame_index = 0                 # fly animation logic set up (used in enemy class, not actually needed here)
fly_surf = fly_frames[fly_frame_index]

obstacle_rect_list = []


player_walk_1 = pygame.image.load('graphics/player/player_walk_1.png').convert_alpha() # player walk images
player_walk_2 = pygame.image.load('graphics/player/player_walk_2.png').convert_alpha()
player_walk = [player_walk_1,player_walk_2]
player_index = 0
player_jump = pygame.image.load('graphics/player/jump.png').convert_alpha()

player_surf = player_walk[player_index]
player_rect = player_surf.get_rect(midbottom = (80,300))
player_gravity = 0

# Intro screen
player_stand = pygame.image.load('graphics/player/player_stand.png').convert_alpha()
player_stand = pygame.transform.rotozoom(player_stand,0,2) # images for intro screen 
player_stand_rect = player_stand.get_rect(center = (400,200))

game_name = test_font.render('Pixel Runner',False,(111,196,169))
game_name_rect = game_name.get_rect(center = (400,80))

game_message = test_font.render('Press space to run',False,(111,196,169))
game_message_rect = game_message.get_rect(center = (400,330))

# Timer 
obstacle_timer = pygame.USEREVENT + 1   # Timer for enemy spawn 
pygame.time.set_timer(obstacle_timer,1500)

snail_animation_timer = pygame.USEREVENT + 2
pygame.time.set_timer(snail_animation_timer,500) # timer to make snail animation

fly_animation_timer = pygame.USEREVENT + 3
pygame.time.set_timer(fly_animation_timer,200) # timer to make fly animation

while True: # loop until game over screen 
	for event in pygame.event.get(): # if exit is hit in game window, game ends 
		if event.type == pygame.QUIT: 
			pygame.quit()
			exit()
		
		if game_active:
			if event.type == pygame.MOUSEBUTTONDOWN:
				if player_rect.collidepoint(event.pos) and player_rect.bottom >= 300: # if player clicked on he jumps 
					player_gravity = -20
			
			if event.type == pygame.KEYDOWN: # if space is pressed player jumps 
				if event.key == pygame.K_SPACE and player_rect.bottom >= 300:
					player_gravity = -20
		else:
			if event.type == pygame.KEYDOWN and event.key == pygame.K_SPACE: # if in gameover screen and space is pressed then game restarts 
				game_active = True
				
				start_time = int(pygame.time.get_ticks() / 1000) # resets start time 0 so score doesnt carry over 

		if game_active:
			if event.type == obstacle_timer:
				obstacle_group.add(Obstacle(choice(['fly','snail','snail','snail']))) # setting amount of snail swans higher than fly spawns 

			if event.type == snail_animation_timer: # timer used to set snail slither speed 
				if snail_frame_index == 0: snail_frame_index = 1
				else: snail_frame_index = 0
				snail_surf = snail_frames[snail_frame_index] 

			if event.type == fly_animation_timer: # timer used to set fly slither speed 
				if fly_frame_index == 0: fly_frame_index = 1
				else: fly_frame_index = 0
				fly_surf = fly_frames[fly_frame_index] 


	if game_active: # game screen while active 
		screen.blit(sky_surface,(0,0)) # sky BG
		screen.blit(ground_surface,(0,300)) # ground BG
		score = display_score() # function in main to display score

		player.draw(screen) # draws player class sprite on to screen 
		player.update() # upadtes player class attributes all in one function call

		obstacle_group.draw(screen) # draws class enemies to screen 
		obstacle_group.update() # updates both class enimes in one function 

		# collision 
		game_active = collision_sprite() # collision detection with a class function 
		
	else: # start/game over screen 
		screen.fill((94,129,162)) # screen colour
		screen.blit(player_stand,player_stand_rect) # big player character in middle of screen 
		obstacle_rect_list.clear() # clear enemy list once game over 
		player_rect.midbottom = (80,300) # center image player in middle of screen
		player_gravity = 0 # resets player gravity to 0 so when you restart he is not floating 

		score_message = test_font.render(f'Your score: {score}',False,(111,196,169)) # displays last score to game over screen 
		score_message_rect = score_message.get_rect(center = (400,330))
		screen.blit(game_name,game_name_rect)

		if score == 0: screen.blit(game_message,game_message_rect) # if no last score is detected (first time playing game) then game instructions are dispalyed instead 
		else: screen.blit(score_message,score_message_rect)

	pygame.display.update() # upadtes game screen each frame 
	clock.tick(60) # sets the clock to capped 60 fps