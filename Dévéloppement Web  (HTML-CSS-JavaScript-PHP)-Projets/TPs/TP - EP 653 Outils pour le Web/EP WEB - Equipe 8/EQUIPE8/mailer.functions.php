<?php
/*
# -----------------------------------------------------------------------
# Name ...... : PHPMailer Custom Functions.
# Info ...... : Sending e-mail from remote SMTP servers. 
# Author .... : Rainner Lins ( rainnerlins@gmail.com ) 
# Site ...... : http://rainnerlins.com/  
# License ... : http://opensource.org/licenses/mit-license.php 
# Date ...... : MAR-21-2011 
# -----------------------------------------------------------------------
# THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND,
# EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF
# MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND
# NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE
# LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION
# OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION
# WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
# -----------------------------------------------------------------------
*/

// PHPMailer Class file 
include_once( 'class.phpmailer.php' ); 

// Class instance and sender details 
$m = new PHPMailer(); 
$m->IsSMTP(); 
$m->SMTPDebug  = 2; // 0 to turn off debugging

// Who is sending this message 
$m->FromName   = "Site Owner"; 
$m->From       = "admin@mydomain.com"; 

// Default - Use your local server to send the message 
$m->SMTPAuth   = false;  
$m->SMTPSecure = ""; 
$m->Port       = 25; 
$m->Mailer     = "mail"; 
$m->Host       = "localhost";  

// Yahoo server authentication info 
function smtp_use_yahoo()
{
	global $m; 
	$m->SMTPAuth   = true;  
	$m->SMTPSecure = ""; 
	$m->Mailer     = "smtp"; 
	$m->Host       = "smtp.mail.yahoo.com";
	$m->Username   = "-YAHOO-ACCOUNT-LOGIN-NAME-"; 
	$m->Password   = "-YAHOO-ACCOUNT-PASSWORD-"; 
}

// Gmail server authentication info 
function smtp_use_gmail()
{
	global $m; 
	$m->SMTPAuth   = true; 
	$m->SMTPSecure = "ssl";  
	$m->Port       = 465; 
	$m->Mailer     = "smtp";  
	$m->Host       = "smtp.gmail.com";  
	$m->Username   = "-GMAIL-ACCOUNT-ADDRESS-"; 
	$m->Password   = "-GMAIL-ACCOUNT-PASSWORD-"; 
}

// Who to send to and Message
function send_mail( $to='', $subject='', $message='' )
{
	global $m; 
	$m->Subject = trim( $subject ); 
	$m->AddAddress( $to, $to );  
	$m->MsgHTML( $message ); 
	return $m->Send(); 
} 

?>